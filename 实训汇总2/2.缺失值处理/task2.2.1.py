# -*- coding: utf-8 -*-
"""
Created on Tue Mar 31 13:35:51 2020

@author: meika
"""

import pandas as pd
import numpy as np
import re

df = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\source\中医病案数据.xlsx",sheet_name="中医病案sample")
data = df['现病史、自诉、症状'].astype(str)

#筛选出【现病史、自诉、症状】中含有*病|*痛|*炎|失眠的所有词
pattern1 = re.compile("[。，]([\u4e00-\u9fa5]{1,5}病){1}")
pattern2 = re.compile("[。，]([\u4e00-\u9fa5]{1,5}痛)")
pattern3 = re.compile("[。，]([\u4e00-\u9fa5]{1,5}炎)")
pattern4 = re.compile("(失眠)")
ls = pd.DataFrame()
ls[0] = data
ls[1] = df['主诉']
ls[2] = data.apply(lambda x:re.findall(pattern1,x))
ls[3] = data.apply(lambda x:re.findall(pattern2,x))
ls[4] = data.apply(lambda x:re.findall(pattern3,x))
ls[5] = data.apply(lambda x:re.findall(pattern4,x))

#合并列存放到列1中
for i in range(0,len(df['主诉'])):
    #print(df['主诉'][i],i)
    if(df['主诉'][i] is np.nan):
      str1 = (str(ls[2][i])+str(ls[3][i])+str(ls[4][i])+str(ls[5][i]))
      str2 = re.sub("['\[\]]","",str1)
      ls[1][i] = str(ls[1][i]).replace('nan','')+str2
      
df['主诉'] = ls[1]
df.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\2.缺失值处理\中医病案数据(填补).xlsx",sheet_name="中医病案sample",index=False)
