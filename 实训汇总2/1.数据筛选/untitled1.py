# -*- coding: utf-8 -*-
"""
Created on Mon Mar 30 22:21:44 2020

@author: meika
"""

import pandas as pd
import numpy as np
data = pd.read_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\source\中医病案数据.xlsx",sheet_name="中医病案sample")
#（1）数据筛选：根据主诉与现病史、自诉和症状两个属性，保留与由失眠相关症状的病人信息。
data["主诉"] = data["主诉"].fillna("无")
data["主诉"] = data["主诉"].apply(str)
data = data[data["主诉"].str.contains("失眠")]
data.to_excel(r"C:\Users\meika\Desktop\Temp\蔡\实训汇总2\1.数据筛选\中医病案数据(失眠).xlsx",sheet_name="中医病案sample",index=False)
