# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 21:01:12 2020

@author: meika
"""

import pandas as pd
import numpy as np

path = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总3\中医病案数据(一致性).xlsx"

#实验二处理后的文件已经根据id，将主诉同步（即相同id有相同的主诉，相同id存在多个）
df = pd.read_excel(path,sheet_name = "中医病案sample")
df = df.fillna(" ")
df = df[df["主诉"].str.contains("失眠")]

df.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总3\中医病案数据(失眠Final).xlsx",sheet_name ="中医病案sample",index=False)
