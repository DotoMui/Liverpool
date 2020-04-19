# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 22:03:28 2020

@author: meika
"""

#因为pynlpir库不能很好地支持py3.7 所以切换为py2._版本
import os

#pynlpir.open(encoding="utf8")

srcpath = r"C:\Users\meika\Desktop\src"
#input_path1 = "C:/Users/meika/Desktop/src/"
#input_path2 = "C:/Users/meika/Desktop/src/"
input_path1 = "C:/Users/meika/Desktop/Temp/蔡/实训汇总3/2.生成文本文件/失眠症状文本/"
input_path2 = "C:/Users/meika/Desktop/Temp/蔡/实训汇总3/2.生成文本文件/失眠处方文本/"

#将txt文件合并，方便读取
def mergeText(input_path):
    print(input_path)
    whole_file = [os.path.join(input_path,file) for file in os.listdir(input_path)]
    
    content = []
    for w in whole_file:
        with open(w,'rb') as f:
            content = content+f.readlines()
            
    output_path = os.path.join(input_path,'#output.txt')
                               
    with open(output_path,'wb') as f:
        f.writelines(content)
    print("Finished")

if __name__ == '__main__':
    mergeText(input_path1)
    mergeText(input_path2)