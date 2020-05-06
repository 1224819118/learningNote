## Shell基础

Shell就是一个命令解释器，将我们的命令解释给Linux内核，其实我们平时打命令的界面就是Shell他的功能呢就是给我们一个可以与系统交互的界面

Shell是解释执行的脚本语言，不需要编译，所见即所得。

sh 进入bash  bshell



## Shell编程

$? 显示上一个命令的执行状态

$(#name) 统计字符长度

Expr length "$name". 也是统计字符长度

${name:2} 字符的截取 从第二个开始截取

${name:2:2} 从第二个开始截取截取两个

双小括号

$((1+2)) 用于计算

变量输入

read -t 10 -p "please input one num": num

输入一个数字传给num





协议个计算器

```
#!/bin/bash
#该脚本是写一个简单的计算器

read -p "请输入第一个数字" x
read -p "请输入第二个数字" y
read -p "请输入运算符号" f

#最外层判断，判断输入的是否为空
# -n 判断 是否为非空
# -a 多重条件判断 与
if [ -n "$x" -a -n "$y" -a -n "$f" ]
        then
# 下面这个表达式用于判断输入的两个字符是否完全为数字，反引号 和 $()为同一个作用，将系统命令的值赋给变量，原理为，输出变量x的值，并利用管道符，将该值进行字符串替换， sed "s/旧字符串/新字符串/g"，末尾g表示将指定范围内的所有旧字符串都替换，所以虽然[0-9]表示匹配一个字符，加了g以后，会替换所有字符。
                test1=`echo "$x" | sed "s/[0-9]//g"`
                test2=$(echo "$y" | sed "s/[0-9]//g")
#中间的判断，判断输入的两个值是否为数字
            if [ -z "$test1" -a -z "$test2" ]
                    then
# 最内层判断，判断是什么运算符号
                            if [ "$f" == "+" ]
                                    then
                                    echo "$x和$y的和是"$[$x+$y]
                            elif [ "$f" == "-" ]
                                    then
                                    echo "$x和$y的差是"$[$x-$y]
                            elif [ "$f" == "*" ]
                                    then
                                    echo "$x和$y的积是"$[$x*$y]
                            elif [ "$f" == "/" ]
                                    then
                                        echo "$x和$y的商是"$[$x/$y]
                            fi
            else
                    echo "您输入的不是数字，重新执行脚本"
                    bash jisuanqi.sh
                    exit 2
            fi
    else
            echo "您没有输入数字，重新执行脚本"
            bash jisuanqi.sh
            exit 1
fi


```

