# -*- coding: utf-8 -*-
"""
Created on Fri Mar 13 00:20:09 2020

@author: meika
"""


'''关键词提取'''
import pynlpir
text_path = "C:/Users/meika/Desktop/text.txt"

result = pynlpir.nlpir.GetFileKeyWords(text_path,200,True)

for group in result.split("#"):
    try:
        w,f,wt,fq = group.split("/")
        print w,f,wt,fq
    except:
        pass
    

'''停用词过滤'''
textout_path = "C:/Users/meika/Desktop/text_out.txt"
stopwords_path = "C:/Users/meika/Desktop/stopwords.txt"
stopwords = []
for line in open(stopwords_path).readlines():
    stopwords.append(line.strip())      
    
out = open("C:/Users/meika/Desktop/text_filtered.txt",'a+')  
 
for line in open(textout_path,"r").readlines():
    remain=[]
    word_list = line.strip().split(" ")
    if len(word_list)>1:
        for word in word_list:
            g = word.split("/")
            if len(g)>1:
                [w,f] = g
                if 'g' not in w:    #去除中药的重量
                    if w not in stopwords:
                        remain.append(word)
    out.write(" ".join(remain)+"\n")
    
out.close();