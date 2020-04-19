# -*- coding: utf-8 -*-
"""
Created on Sat Apr 18 16:50:20 2020

@author: meika
"""
import jieba
import jieba.posseg as pseg
import re
import gensim
from gensim.models import Word2Vec  
import os

input_path1 = "C:/Users/meika/Desktop/Temp/蔡/实训汇总3/2.生成文本文件/失眠症状文本/"
input_path2 = "C:/Users/meika/Desktop/Temp/蔡/实训汇总3/2.生成文本文件/失眠处方文本/"
dict_dir1 = r"C:/Users/meika/Desktop/src/#dictCF.txt"
dict_dir2 = r"C:/Users/meika/Desktop/src/#dictZZ.txt"
pattern = re.compile("{d}+")

#读取文件路径，获得分词列表
def scanFile(input_path,dict_dir):  
    whole_file = [os.path.join(input_path,file) for file in os.listdir(input_path)]
    #用列表接收
    ls =[]
    for src_dir in whole_file:
        print(src_dir)
        ls.append(fenCi(src_dir,dict_dir))
    return ls

#每个文本都做分词处理
def fenCi(src_dir,dict_dir):
    #读取txt文本
    f = open(src_dir,"a+",encoding='utf-8')
    f.seek(0)
    data = f.read()
    
    #加载用户词典
    jieba.load_userdict(dict_dir)
    
    words = pseg.cut(data) #使用paddle模式
    
    #存入分词文本
    del_dict=["\"","，"," ","。","\n","(",")","g","、","-","/","#","（","）","h",",","：","“","”"] #去除特点词
    ls = []
    for word, flag in words:
        if word not in del_dict:
            if word.isdigit():
                #print(type(word),type("www"),"==去掉数字==")
                pass
            else:
                ls.append(word)
    f.close()
    return ls

def word2vecModel(sentence,filename):
    modelZZ=gensim.models.Word2Vec(sentence,sg=1,size=100,window=5,min_count=2,negative=3,sample=0.001,hs=1,workers=4)
    modelZZ.wv.save_word2vec_format(filename)
    #model.save("model") 保存到本地,是乱码
    
if __name__ == '__main__':
    
    lsZZ = scanFile(input_path1,dict_dir1)
    print("=====症状分词完成=====")
    lsCF = scanFile(input_path2,dict_dir2)
    print("=====处方分词完成=====")
    
    #症状模型
    word2vecModel(lsZZ,"modelZZ")
    print("=====症状模型建立完成=====")
    #处方模型
    word2vecModel(lsCF,"modelCF")
    print("=====处方模型建立完成=====")
    
    #如果对..wv.save_word2vec_format加载的模型进行追加训练，会报错：可以对save追加训练
