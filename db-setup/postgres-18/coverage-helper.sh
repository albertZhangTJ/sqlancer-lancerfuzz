#!/bin/bash
# PostgreSQL Coverage Collection Helper Script

set -e

PGDATA="${PGDATA:-/var/lib/postgresql/data}"
COVERAGE_DIR="/coverage"
PG_SOURCE="/opt/postgresql-18.1"

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

function print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

function print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

function print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to start PostgreSQL
function start_postgres() {
    print_info "Starting PostgreSQL..."
    pg_ctl -D "$PGDATA" -l "$PGDATA/logfile" start
    sleep 2
    print_success "PostgreSQL started"
}

# Function to stop PostgreSQL
function stop_postgres() {
    print_info "Stopping PostgreSQL..."
    pg_ctl -D "$PGDATA" stop -m fast || true
    print_success "PostgreSQL stopped"
}

# Function to reset coverage data
function reset_coverage() {
    print_info "Resetting coverage data..."
    cd "$PG_SOURCE"
    
    # Remove old .gcda files (accumulated coverage data)
    find . -name "*.gcda" -delete
    
    # Reset lcov counters
    lcov --zerocounters --directory . 2>/dev/null || true
    
    print_success "Coverage data reset"
}

# Function to collect coverage data
function collect_coverage() {
    print_info "Collecting coverage data..."
    cd "$PG_SOURCE"
    
    # Create coverage directory if it doesn't exist
    mkdir -p "$COVERAGE_DIR"
    
    # Capture coverage data using lcov
    lcov --capture \
         --directory . \
         --output-file "$COVERAGE_DIR/coverage.info" \
         --rc lcov_branch_coverage=0 \
         2>&1 | grep -v "ignoring data for external file" || true
    
    # Remove system files from coverage report
    lcov --remove "$COVERAGE_DIR/coverage.info" \
         '/usr/*' \
         '*/test/*' \
         --output-file "$COVERAGE_DIR/coverage_filtered.info" \
         --rc lcov_branch_coverage=0 \
         2>/dev/null || true
    
    print_success "Coverage data collected at $COVERAGE_DIR/coverage_filtered.info"
}

# Function to generate HTML coverage report
function generate_html_report() {
    print_info "Generating HTML coverage report..."
    cd "$PG_SOURCE"
    
    if [ ! -f "$COVERAGE_DIR/coverage_filtered.info" ]; then
        print_error "No coverage data found. Run collect_coverage first."
        return 1
    fi
    
    # Generate HTML report
    genhtml "$COVERAGE_DIR/coverage_filtered.info" \
            --output-directory "$COVERAGE_DIR/html" \
            --title "PostgreSQL Fuzzing Coverage Report" \
            --legend \
            --rc lcov_branch_coverage=0 \
            2>/dev/null || true
    
    print_success "HTML report generated at $COVERAGE_DIR/html/index.html"
    print_info "To view: open $COVERAGE_DIR/html/index.html in a browser"
}

# Function to show coverage summary
function show_summary() {
    if [ ! -f "$COVERAGE_DIR/coverage_filtered.info" ]; then
        print_error "No coverage data found. Run collect_coverage first."
        return 1
    fi
    
    print_info "Coverage Summary:"
    lcov --list "$COVERAGE_DIR/coverage_filtered.info" | head -20
}

# Function to run a fuzzing cycle with coverage
function fuzz_cycle() {
    print_info "Running fuzzing cycle with coverage..."
    
    # Reset coverage
    reset_coverage
    
    # Start PostgreSQL
    start_postgres
    
    # Wait for connections
    sleep 2
    
    print_info "PostgreSQL is running. Run your fuzzer now."
    print_info "Press ENTER when fuzzing is complete to collect coverage..."
    read
    
    # Stop PostgreSQL
    stop_postgres
    
    # Collect coverage
    collect_coverage
    
    # Show summary
    show_summary
}

# Function to export coverage in different formats
function export_coverage() {
    FORMAT="${1:-json}"
    print_info "Exporting coverage in $FORMAT format..."
    
    if [ ! -f "$COVERAGE_DIR/coverage_filtered.info" ]; then
        print_error "No coverage data found. Run collect_coverage first."
        return 1
    fi
    
    case "$FORMAT" in
        json)
            lcov --list "$COVERAGE_DIR/coverage_filtered.info" | \
            awk 'NR>3 {print "{\"file\":\""$1"\",\"lines\":\""$2"\",\"functions\":\""$3"\"}"}' \
            > "$COVERAGE_DIR/coverage.json"
            print_success "Coverage exported to $COVERAGE_DIR/coverage.json"
            ;;
        xml)
            # This requires lcov_cobertura or similar tool
            print_info "XML export requires additional tools (lcov_cobertura)"
            ;;
        *)
            print_error "Unknown format: $FORMAT"
            return 1
            ;;
    esac
}

# Main command handler
case "${1:-}" in
    start)
        start_postgres
        ;;
    stop)
        stop_postgres
        ;;
    reset)
        reset_coverage
        ;;
    collect)
        collect_coverage
        ;;
    report)
        generate_html_report
        ;;
    summary)
        show_summary
        ;;
    cycle)
        fuzz_cycle
        ;;
    export)
        export_coverage "${2:-json}"
        ;;
    *)
        echo "PostgreSQL Fuzzing Coverage Helper"
        echo ""
        echo "Usage: $0 {start|stop|reset|collect|report|summary|cycle|export}"
        echo ""
        echo "Commands:"
        echo "  start    - Start PostgreSQL server"
        echo "  stop     - Stop PostgreSQL server"
        echo "  reset    - Reset coverage data"
        echo "  collect  - Collect coverage data"
        echo "  report   - Generate HTML coverage report"
        echo "  summary  - Show coverage summary"
        echo "  cycle    - Run complete fuzzing cycle with coverage"
        echo "  export   - Export coverage data (json|xml)"
        echo ""
        echo "Example workflow:"
        echo "  1. $0 reset          # Reset coverage"
        echo "  2. $0 start          # Start PostgreSQL"
        echo "  3. [Run your fuzzer]"
        echo "  4. $0 stop           # Stop PostgreSQL"
        echo "  5. $0 collect        # Collect coverage"
        echo "  6. $0 report         # Generate report"
        echo ""
        echo "Or simply:"
        echo "  $0 cycle             # Interactive fuzzing cycle"
        ;;
esac
