
# number of samples
nreps = 10000
# number of observations in each sample
nobs = 100
# number of trials in each observation
trials = 5
# probability of "success" in each trial
ps = .1

# generate data
binomSamps = lapply(rep(nobs,nreps), rbinom, size=trials, prob=ps)

# compute sample means
mymeans = sapply(binomSamps, mean)

# draw a histogram
hist(mymeans, freq=FALSE)

# draw a normal probability plot
qqnorm(mymeans)

centralLimit = function(nreps, nobs, trials, ps, hist = FALSE, qqnorm = FALSE, ...)
{
  binomSamps = lapply(rep(nobs,nreps), rbinom, size=trials, prob=ps)
  mymeans = sapply(binomSamps, mean)
  if (hist == TRUE)
  {
    hist(mymeans, freq=FALSE, ...)
  }
  if (qqnorm == TRUE)
  {
    qqnorm(mymeans, ...)
  }
}

centralLimit(nreps = 100000, nobs = 5, trial = 5, ps = 0.1, hist = TRUE, qnorm = TRUE)

