# -*- coding: utf-8 -*-
"""
Created on Fri Mar 13 00:05:53 2020

@author: meika
"""


import pynlpir
pynlpir.open(encoding="utf8")

#导入用户词典
usrdict_path = "C:/Users/meika/Desktop/usrdict.txt"
out_path = "C:/Users/meika/Desktop/text_out.txt"
text_path = "C:/Users/meika/Desktop/text.txt"

pynlpir.nlpir.ImportUserDict(usrdict_path)
pynlpir.nlpir.FileProcess(text_path,out_path,True)