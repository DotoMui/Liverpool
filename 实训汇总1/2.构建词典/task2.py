# -*- coding: utf-8 -*-
"""
Created on Thu Mar 12 23:49:02 2020

@author: meika
"""

'''获取新词,构建用户词典'''
import pynlpir
pynlpir.open(encoding="utf8")

text_path = "C:/Users/meika/Desktop/text.txt"
usrdict_path = "C:/Users/meika/Desktop/usrdict.txt"

f = open(usrdict_path,'a+')

#获取文件新词
result =  pynlpir.nlpir.GetFileNewWords(text_path,200,False)

for group in result.split("#"):
    f.write(group+'\n')

f.close()

