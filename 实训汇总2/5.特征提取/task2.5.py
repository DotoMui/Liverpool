# -*- coding: utf-8 -*-
"""
Created on Wed Apr  1 12:15:07 2020

@author: meika
"""
import pandas as pd
import numpy as np
import re
df = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\3.分解\中医病案数据(分解).xlsx",sheet_name="中医病案sample")

df["性别"] = df["性别"].fillna("无")

grouped = df.groupby("性别")

#根据性别分成3个数据框
dfSex1 = grouped.get_group('女')
dfSex2 = grouped.get_group('男')
dfSex3 = grouped.get_group('无')

grouped1 = df.groupby("性别")

df["年龄"] = df["年龄"].fillna("无")
dfAge1 = df[df["年龄"] == '成']
dfAge2 = df[df["年龄"] == '小儿']
dfAge3 = df[df["年龄"] == '无']
dfAge4 = df[df['年龄'].apply(lambda x:x!='成' and x!='无')]


path1 = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\5.特征提取\中医病案数据(性别).xlsx"
path2 = r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\5.特征提取\中医病案数据(年龄).xlsx"

writer = pd.ExcelWriter(path1)
dfSex1.to_excel(writer,sheet_name="女",index=False)
dfSex2.to_excel(writer,sheet_name="男",index=False)
dfSex3.to_excel(writer,sheet_name="无",index=False)
writer.save()

writer = pd.ExcelWriter(path2)
dfAge1.to_excel(writer,sheet_name="成",index=False)
dfAge2.to_excel(writer,sheet_name="小儿",index=False)
dfAge3.to_excel(writer,sheet_name="无",index=False)
dfAge4.to_excel(writer,sheet_name="其他",index=False)
writer.save()
