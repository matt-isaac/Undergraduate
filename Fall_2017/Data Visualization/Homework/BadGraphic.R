library(ggplot2)
dataf = read.csv("population_data.csv", header = TRUE)

year = dataf[,1]
pop = dataf[,2]
state = dataf[,3]


dataf = data.frame(time, pop, state)

ggplot(dataf, aes(x=year, y=pop, group = state, color = state)) +
  xlab("Population (Thousands)") + 
  ylab("Year") + 
  ggtitle("Population by Official Census") + 
  geom_line() +
  geom_point() 
  

plot(pop ~ state, type = "o", col = "red")
lines(time, ny, type = "o", col = "blue")

