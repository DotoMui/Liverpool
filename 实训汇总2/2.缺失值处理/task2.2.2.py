# -*- coding: utf-8 -*-
"""
Created on Tue Mar 31 21:38:45 2020

@author: meika
"""

import pandas as pd
import numpy as np
import re

#在现病史、自诉和症状列中，有些复诊病人的记录是病史如前又补充了一些内容，需把复诊病人的该列补充完整。
df = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\2.缺失值处理\中医病案数据(填补).xlsx",sheet_name="中医病案sample")

ls = pd.DataFrame()
ls[0] = df['病人ID'].astype(str)
ls[1] = df['主诉'].astype(str)
#根据id合并
ls = ls.groupby(0)[1].agg(",".join)


for i in df.index:
#    print(df["病人ID"][i],ls[df["病人ID"][i]])
    #set 去重
    set1 = set(ls[df["病人ID"][i]].replace("nan","").split(","))
    df["主诉"][i] = re.sub("['{}]","",str(set1)).replace(","," ")
    #df["主诉"][i]
    
df.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\2.缺失值处理\中医病案数据(填补2).xlsx",sheet_name="中医病案sample",index=False)