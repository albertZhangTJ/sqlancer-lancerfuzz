# install.packages("svglite")
library(svglite)
data <- read.csv("loc_results.csv")
svglite("loc.svg", width=10, height=5)
boxplot(data$LOC,
        xlab = "Lines of Code",
        ylab = "",
        col = "white",
        border = "black",
        horizontal = TRUE
)
dev.off()