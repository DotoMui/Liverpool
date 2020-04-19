# -*- coding: utf-8 -*-
"""
Created on Sat Apr 18 14:54:55 2020

@author: meika
"""

#（5）将文件作带词性标注的分词处理。（jieba）

import jieba
import jieba.posseg as pseg
import os


dict_dir = r"C:/Users/meika/Desktop/src/#dictCF.txt"
src_dir = r"C:/Users/meika/Desktop/src/#outputCF.txt"

#读取txt文本
f = open(src_dir,"a+",encoding='utf-8')
f.seek(0)
data = f.read()

#加载用户词典
jieba.load_userdict(dict_dir)

#带词性分词
jieba.enable_paddle()
words = pseg.cut(data,use_paddle=True) #使用paddle模式
for word, flag in words:
    print('%s %s' % (word, flag))