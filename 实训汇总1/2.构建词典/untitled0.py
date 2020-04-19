# -*- coding: utf-8 -*-
"""
Created on Thu Mar 12 22:59:32 2020

@author: meika
"""


import pynlpir
pynlpir.open(encoding="utf8")

'''分词'''
s = "出现了著名医学家张仲景，他已经对“八纲”（阴阳、表里、虚实、寒热）有所认识，总结了“八法”。华佗则以精通外科手术和麻醉名闻天下，还创立了健身体操“五禽戏”"
seg_list = pynlpir.segment(s,pos_tagging=False)

#for word in seg_list:
#    print word

s= '''整体就是统一性和完整性。中医学非常重视人体本身的统一性、完整性及其与自然界的相互关系，认为人体是一个有机的整体，构成人体的各个组成部分之间在结构上不可分割，在功能上相互协调、互为补充，在病理上则相互影响。而且人体与自然界也是密不可分的，自然界的变化随时影响着人体，人类在能动地适应自然和改造自然的过程中维持着正常的生命活动。这种机体自身整体性和内环境统一性的思想即整体观念。整体观念是中国古代唯物论和辩证思想在中医学中的体现；它贯串于中医学的生理、病理、诊法、辨证和治疗等各个方面。'''
print pynlpir.nlpir.GetNewWords(s,50,False)

text_path = "C:/Users/meika/Desktop/medicine.txt"
print pynlpir.nlpir.GetFileNewWords(text_path,50,False)

out_path = "C:/Users/meika/Desktop/medicine_out.txt"
pynlpir.nlpir.FileProcess(text_path,out_path,True)

'''关键词提取'''
tags = pynlpir.get_key_words(s,weighted=True)

result = pynlpir.nlpir.GetFileKeyWords(text_path,100,True)
for group in result.split("#"):
    try:
        w,f,wt,fq = group.split("/")
        print w,f,wt,fq
    except:
        pass
    
'''停用词过滤'''
stopwords_path = "C:/Users/meika/Desktop/stopwords.txt"
stopwords = []
for line in open(stopwords_path).readlines():
    stopwords.append(line.strip())      
    
for w in seg_list:
    if w.encode("utf8") not in stopwords:
        print w          

'''统计词频'''     
import nltk
words = []
for line in open("C:/Users/meika/Desktop/text_filtered.txt","r").readlines():
    remain = []
    word_list = line.strip().split(" ")
    if len(word_list)>1:
        for word in word_list:
            [w,f] = word.split("/")
            words.append(w)

#统计词频
freq = nltk.FreqDist(words)

for(w,f) in freq.most_common(50):
    print w,f
