# -*- coding: utf-8 -*-
"""
Created on Fri Mar 13 01:27:19 2020

@author: meika
"""

'''统计词频'''
import nltk
words = []
#统计所有词性
for line in open("C:/Users/meika/Desktop/text_filtered.txt","r").readlines():
    remain = []
    word_list = line.strip().split(" ")
    if len(word_list)>1:
        for word in word_list:
            [w,f] = word.split("/")
            words.append(w)
#抽取名词   
words_n =[]
for line in open("C:/Users/meika/Desktop/text_filtered.txt","r").readlines():
    word_list = line.strip().split(" ")
    if len(word_list)>1:
        for word in word_list:
            g = word.split("/")
            if len(g)>1:
                [w,f]=g
                if f[0] in ["n"]:    #v,a
                    print w,f
                    words_n.append(word)
#统计词频
out = open("C:/Users/meika/Desktop/sum_freq_n.csv",'a+') 
freq = nltk.FreqDist(words_n)

for(w,f) in freq.most_common(50):
    print w.rstrip("//ng)"),f
    out.write(w.rstrip("//ng)")+","+str(f)+"\n")
out.close()