# -*- coding: utf-8 -*-
"""
Created on Thu Apr 16 22:47:13 2020

@author: meika
"""


import pynlpir
pynlpir.open(encoding = "utf-8")

srcPath1 = r"C:\Users\meika\Desktop\src\#outputZZ.txt"
srcPath2 = r"C:\Users\meika\Desktop\src\#outputCF.txt"
dictPath1 = r"C:\Users\meika\Desktop\src\#dictZZ.txt"
dictPath2 = r"C:\Users\meika\Desktop\src\#dictCF.txt"

#构建词典
def getDict(dictPath,srcPath):
    f = open(dictPath,'a+')
    
    #获取文件新词
    result =  pynlpir.nlpir.GetFileNewWords(srcPath,1000,False)
    
    for group in result.split("#"):
        f.write(group+'\n')
        
    f.close()
    
if __name__ == '__main__':
    getDict(dictPath1,srcPath1)
    getDict(dictPath2,srcPath2)