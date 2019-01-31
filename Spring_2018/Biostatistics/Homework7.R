# BioStat Homework 7

# Exercise 4
library(metafor)
data <- read.csv("http://www.stat.usu.edu/jrstevens/biostat/data/rosiglitazone.csv")

Fishers.p <- rep(NA,nrow(data))

for(i in 1:nrow(data))
{
  mat <- matrix(as.numeric(data[i,]), ncol = 2)
  Fishers.p[i] <- fisher.test(mat)$p.value
}

summary(data)
data <- data.frame(data)
#round(cbind(data,as.numeric(Fishers.p)),5)
data <- cbind(data, Fishers.p)

a <- data$RosMI # Ros, MI
b <- data$RosNum - data$RosMI # Ros, no MI
c <- data$CtlMI # Ctl, MI
d <- data$CtlNum - data$CtlMI # Ctl, no MI
# theta.hat <- log( (b + 0.5) * (c + 0.5) / ( (a + 0.5) * (d + 0.5) ))
# var.theta.hat <- 1/(b + 0.5) + 1/(c + 0.5) + 1/(a + 0.5) + 1/(d + 0.5)
# w.RE <- 1/var.theta.hat
# theta.RE <- sum(w.RE * theta.hat) / sum(w.RE)
# var.RE <- 1/sum(w.RE)
# z.RE <- theta.RE/sqrt(var.RE)
# p.RE <- 2 * (1 - pnorm(abs(z.RE)))
# round(c(z.RE,p.RE),5)
# OR.RE <- exp(theta.RE)
# OR.RE

result <- rma.uni(
  ai = a,
  bi = b,
  ci = c,
  di= d,
  measure = 'OR',
  add = 0.5, to = 'all',
  method = 'DL',
  slab = 1:42
)
summary(result)

forest(result)
funnel(result)
radial(result)
z <- result$yi/sqrt(result$vi + result$tau2)
x <- 1/sqrt(result$vi + result$tau2)
fit <- lm(z~x)
summary(fit)$coeff


# Peto Method #
result_peto <- rma.uni(
  ai = a,
  bi = b,
  ci = c,
  di= d,
  add = 0, to = 'all',
  measure = 'PETO',
  method = 'DL',
  slab = 1:42
)
summary(result_peto)

forest(result_peto)
funnel(result_peto)
radial(result_peto)
z <- result_peto$yi/sqrt(result_peto$vi + result_peto$tau2)
x <- 1/sqrt(result_peto$vi + result_peto$tau2)
fit <- lm(z~x)
summary(fit)$coeff

ids.drop <- c("49653/127","49653/128","49653/136","49653/143","49653/145",
              "49653/147","49653/162","49653/284","SB-712753/002","SB-712753/003")
data6 <- data[!is.element(data$StudyID,ids.drop),]

a6 <- data6$RosMI # Ros, MI
b6 <- data6$RosNum - data6$RosMI # Ros, no MI
c6 <- data6$CtlMI # Ctl, MI
d6 <- data6$CtlNum - data6$CtlMI # Ctl, no MI

result_peto6 <- rma.uni(
  ai = a6,
  bi = b6,
  ci = c6,
  di= d6,
  add = 0, to = 'all',
  measure = 'PETO',
  method = 'DL',
  slab = 1:32
)
summary(result_peto6)

forest(result_peto6)
funnel(result_peto6)
radial(result_peto6)
z <- result_peto6$yi/sqrt(result_peto6$vi + result_peto6$tau2)
x <- 1/sqrt(result_peto6$vi + result_peto6$tau2)
fit <- lm(z~x)
summary(fit)$coeff
