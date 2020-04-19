# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 21:47:30 2020

@author: meika
"""

import pandas as pd
import numpy as np

path = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总3\中医病案数据(失眠Final).xlsx"
path1 = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总3\2.生成文本文件\失眠症状文本\\"
path2 = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总3\2.生成文本文件\失眠处方文本\\"
df = pd.read_excel(path,sheet_name = "中医病案sample")

#转化为文本文件
    #根据['时间',"病人ID","性别"]分组,目的各自生成一个文本文件
    
#失眠症状文本
for i in df.index:
    str1 = df.iloc[i][5]+"\n"+df.iloc[i][6]+"\n"+df.iloc[i][8]+"\n"+df.iloc[i][9]
    data = pd.Series(str1)
    title = df['病人ID'][i]+df['性别'][i]+df['时间'][i]
    data.to_csv(path1+title+'.txt', sep='\t', index=False)

#失眠处方文本
for i in df.index:
    str1 = df.iloc[i][10]
    data = pd.Series(str1)
    title = df['病人ID'][i]+df['性别'][i]+df['时间'][i]
    data.to_csv(path2+title+'.txt', sep='\t', index=False)