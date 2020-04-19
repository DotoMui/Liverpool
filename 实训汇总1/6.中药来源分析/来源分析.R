library(xlsx)
read.xlsx("C:/Users/meika/Desktop/text/context_1583853237845.xlsx", 1,encoding="UTF-8")
attach(data)
attach(data)
data1 = 处方来源
barplot(table(data1))