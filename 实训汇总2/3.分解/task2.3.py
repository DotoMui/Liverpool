# -*- coding: utf-8 -*-
"""
Created on Wed Apr  1 10:56:16 2020

@author: meika
"""

#（3）分解：对处方中含有各字的中药分解，如煅龙牡是煅牡蛎和煅龙骨两味药；如焦楂曲是炒山楂和炒神曲两味药，等等。
import pandas as pd
import numpy as np
import re
ls = pd.Series(np.arange(2416))
df = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\2.缺失值处理\中医病案数据(填补2).xlsx",sheet_name="中医病案sample")
ls = df["处方1"].astype(str)

#建立替换字典
dic = {'煅龙牡':'煅龙牡(煅牡蛎 煅龙骨)',
       '焦楂曲':'焦楂曲(炒山楂 炒神曲)'}
for i in dic:
    print(i,dic[i])
#ls = ls.apply(lambda x:x.replace(i,dic[i]) for i in dic)

#替换函数
def substitute(string,dic):
    for i in dic:
         string = string.replace(i,dic[i])
    return string
#string = "玄参15 焦楂曲15 龙齿15 黄芩10 黄连10 栀子10 煅龙牡30 黄芪30 桂枝10 白芍15 神曲10 夏枯草10 生晒参10 炙甘草6    14付" 
#print(substitute(string,dic))

ls = ls.apply(lambda x:substitute(x,dic))
df["处方1"] = ls
df.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\3.分解\中医病案数据(分解).xlsx",sheet_name="中医病案sample",index=False)
