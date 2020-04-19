# -*- coding: utf-8 -*-
"""
Created on Wed Apr  1 14:42:47 2020

@author: meika
"""

import pandas as pd
import numpy as np
import re
#有复诊病人的“现病史、自诉和症状列”描述的一致性情况。各列的值是否描述了相应内容，如脉象、舌象是否混杂。
df = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\3.分解\中医病案数据(分解).xlsx",sheet_name="中医病案sample")
a = df.groupby(["现病史、自诉、症状"]).count()>1
price = a[a['病人ID'] == True].index
repeat_df = df[df['现病史、自诉、症状'].isin(price)]
#输出相同'现病史、自诉、症状'，放便人工修改

df.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\4.一致性\中医病案数据(一致性).xlsx",sheet_name="中医病案sample",index=False)
