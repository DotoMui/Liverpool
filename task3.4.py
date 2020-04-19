# -*- coding: utf-8 -*-
"""
Created on Fri Apr 17 21:52:11 2020

@author: meika
"""
import jieba
import jieba.posseg as pseg
import os

#jieba

dict_dir1 = r"C:/Users/meika/Desktop/src/#dictCF.txt"
src_dir1 = r"C:/Users/meika/Desktop/src/#outputCF.txt"
dict_dir2 = r"C:/Users/meika/Desktop/src/#dictZZ.txt"
src_dir2 = r"C:/Users/meika/Desktop/src/#outputZZ.txt"
fenci_dir1 = r"C:/Users/meika/Desktop/src/#fenciCF.txt"
fenci_dir2 = r"C:/Users/meika/Desktop/src/#fenciZZ.txt"

#停用词
#def delWord():
#    ls = ["。","，","\""]
#    for i in ls:
#        jieba.del_word(i)

#（4）将文件作分词处理。（jieba）
def fenCi(dict_dir,src_dir):
    f = open(src_dir,"a+",encoding='utf-8')
    f.seek(0)
    data = f.read()
    
    #加载用户词典
    jieba.load_userdict(dict_dir)
    
    #精确模式
    words = jieba.cut(data, cut_all=False,use_paddle = True)
    
    print(", ".join(words))
    f.close()

#（5）将文件作带词性标注的分词处理。（jieba）
def ciXing(dict_dir,src_dir,fenci_dir):
    #读取txt文本
    f = open(src_dir,"a+",encoding='utf-8')
    f.seek(0)
    data = f.read()
    
    #加载用户词典
    jieba.load_userdict(dict_dir)
    
    #带词性分词
    jieba.enable_paddle()
    words = pseg.cut(data,use_paddle=True) #使用paddle模式
    outputs = open(fenci_dir, 'w', encoding='utf-8')
    
    #存入分词文本
    for word, flag in words:
        str1 = ('%s %s' % (word, flag))
        outputs.write(str1)
        
    outputs.close()
    f.close()

if __name__ =="__main__":
    fenCi(dict_dir1,src_dir1)
    #ciXing(dict_dir1,src_dir1,fenci_dir1)
    #ciXing(dict_dir2,src_dir2,fenci_dir2)