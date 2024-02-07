# install.packages("svglite")
library(svglite)
data <- read.csv("loc_results.csv")
svglite("loc.svg", width=13, height=5)
boxplot(data$LOC,
        main = "Size of DBMS-specific code",
        xlab = "Effective Lines of Code",
        ylab = "",
        col = "white",
        border = "brown",
        horizontal = TRUE,
        notch = TRUE
)
dev.off()