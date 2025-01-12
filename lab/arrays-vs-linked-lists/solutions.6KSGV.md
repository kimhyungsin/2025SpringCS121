---
title: "Lab 5: Arrays vs Linked Lists"
layout: default
---

# {{ page.title }}
The goal of this lab is to practice analyzing the performance of data structures. Specifically, you will compare the performance characteristics of Java Arrays and LinkedLists.

## Array: Retrieval of an element by index

Here are my results for the retrieval of an element by index, calculated using 10,000,000 trials.

I ran the program multiple times, doubling the size of the data structure each time. I used the data to estimate the exponent $$b$$ in the growth rate equation, as shown in the third column of the table.

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 10000 | 30.6725031 | ... |
| 20000 | 29.9520132 | $$\text{ln}(29.9520132 \div 30.6725031)/\text{ln}(2) \approx -0.03429 \approx 0 $$ |
| 40000 | 30.8575394 | ... |
| 80000 | 30.5059546 | ... |
| 160000 | 30.5368424 | ... |
| 320000 | 31.28404 | ... |
| 640000 | 30.8744206 | $$\text{ln}(30.8744206 \div 31.28404)/\text{ln}(2) \approx -0.01901 \approx 0 $$ |
| 1280000 | 31.1663318 | ... |
| 2560000 | 31.0351684 | ... |
| 5120000 | 31.1552376 | ... |
| 10240000 | 31.493183 | $$\text{ln}(31.493183 \div 31.1552376)/\text{ln}(2) \approx 0.01556 \approx 0 $$ |

You are welcome to use [this spreadsheet](https://docs.google.com/spreadsheets/d/1JZ53B3j1wzJpohNd0Db-mFcY5tPdChAKrouTl5ap-CI/edit?usp=sharing) to perform the log ratio calculations, and to generate a graph of the data. If you use the spreadsheet, inspect the forumula in the third column, so you understand how $$b$$ is calculated.

My experiments suggest that:

$$ b \approx 0 $$

$$ \text{Running time} = a N^0 = a $$

That is, retrieval of an element by index is a constant order operation. Based on the data, it seems that $$a \approx 30$$ nanoseconds. So:

$$ \text{Running time} \approx 30 \text{ nanoseconds} $$

Now, this constant may differ from one computer to another. So we are more interested in the Big Theta runtime complexity, which is:

$$ \Theta(1) $$

{% include image.html src="array_e_by_i.svg" alt="Graph of time taken to retrieve elements by index from arrays of increasing size. The graph shows that it takes approximately the same time, regardless of the size of the array." %}

## LinkedList: Retrieval of an element by index

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 100 | 80.8626151 | ... |
| 200 | 114.1627845 | $$\text{ln}(114.16 \div 80.86)/\text{ln}(2) \approx 0.4976 $$ |
| 400 | 177.8334702 | ... |
| 800 | 311.4314973 | $$\text{ln}(311.43 \div 177.83)/\text{ln}(2) \approx 0.8084 $$ |
| 1600 | 638.1578144 | ... |
| 3200 | 1277.0284176 | $$\text{ln}(1277.03 \div 638.16)/\text{ln}(2) \approx 1.0008 $$ |
| 6400 | 2548.225556 | $$\text{ln}(2548.225556 \div 1277.0284176)/\text{ln}(2) \approx 0.99670 $$ |
| 12800 | 6042.810133 | $$\text{ln}(6042.810133 \div 2548.225556)/\text{ln}(2) \approx 1.2457 $$ |
| 25600 | 11777.93712 | $$\text{ln}(11777.93712 \div 6042.810133)/\text{ln}(2) \approx 0.962795 $$ |
| 51200 | 25481.02052 | $$\text{ln}(25481.02052 \div 11777.93712)/\text{ln}(2) \approx 1.1133 $$ |
| 102400 | 59164.09308 | $$\text{ln}(59164.09308 \div 25481.02052)/\text{ln}(2) \approx 1.2153 $$ |
| 204800 | 171321.4955 | $$\text{ln}(171321.4955 \div 59164.09308)/\text{ln}(2) \approx 1.5339 $$ |

$$ b \approx 1 $$

$$ \text{Running time} = a N^1 $$

$$ 1277.03 = a \times 3200^1 $$

$$ a = 1277.03 \div 3200 \approx 0.3991 $$

$$ \text{Running time} = 0.3991 N $$

$$ \Theta(N) $$

{% include image.html src="ll_e_by_i.svg" alt="Graph of time taken to retrieve elements by index from linked lists of increasing size. The graph shows that as the size of the linked list grows, the time taken increases approximately linearly." %}

## Array: Retrieval of an element's index

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 10000 | 120.373061 | ... |
| 20000 | 148.5824794 | $$\text{ln}(148.58 \div 120.37)/\text{ln}(2) \approx 0.3038 $$ |
| 40000 | 178.1424802 | ... |
| 80000 | 209.8731702 | ... |
| 160000 | 273.6700752 | ... |
| 320000 | 446.2393232 | ... |
| 640000 | 605.7618097 | $$\text{ln}(605.76 \div 446.24)/\text{ln}(2) \approx 0.4409 $$ |
| 1280000 | 782.7458159 | ... |
| 2560000 | 1015.9309653 | ... |
| 5120000 | 1201.5979186 | ... |
| 10240000 | 1438.4563355 | ... |
| 20480000 | 1661.3293651 | $$\text{ln}(1661.33 \div 1438.46)/\text{ln}(2) \approx 0.2078 $$ |

$$ \Theta(\text{log}(N)) $$

{% include image.html src="array_i_by_e.svg" alt="Graph of time taken to locate the index of an element from arrays of increasing size. The graph shows that as the size of the array grows, the time taken increases approximately logarithmically." %}

## LinkedList: Retrieval of an element's index

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 100 | 126.7133813 | ... |
| 200 | 221.7097517 | $$\text{ln}(221.71 \div 126.71)/\text{ln}(2) \approx 0.8071 $$ |
| 400 | 428.3831979 | ... |
| 800 | 880.1156254 | $$\text{ln}(880.12 \div 428.38)/\text{ln}(2) \approx 1.0388 $$ |
| 1600 | 1945.4760063 | ... |
| 3200 | 4184.9204649 | $$\text{ln}(4184.92 \div 1945.48)/\text{ln}(2) \approx 1.1051 $$ |
| 6400 | 9016.440302 | ... |
| 12800 | 20649.015981 | ... |
| 25600 | 46076.23771 | $$\text{ln}(46076.23771 \div 20649.015981)/\text{ln}(2) \approx 1.1579 $$ |
| 51200 | 94029.12676 | $$\text{ln}(94029.12676 \div 46076.23771)/\text{ln}(2) \approx 1.02908 $$ |
| 102400 | 280506.37261 | $$\text{ln}(280506.37261 \div 94029.12676)/\text{ln}(2) \approx 1.57685 $$ |
| 204800 | 1032737.9219 | $$\text{ln}(1032737.9219 \div 280506.37261)/\text{ln}(2) \approx 1.8804 $$ |

$$ b \approx 1 $$

$$ \text{Running time} = a N^1 $$

$$ 4184.92 = a \times 3200^1 $$

$$ a = 4184.92 \div 3200 \approx 1.3078 $$

$$ \text{Running time} = 1.3078 N $$

$$ \Theta(N) $$

{% include image.html src="ll_i_by_e.svg" alt="Graph of time taken to locate the index of an element from linked lists of increasing size. The graph shows that as the size of the linked list grows, the time taken increases approximately linearly." %}
