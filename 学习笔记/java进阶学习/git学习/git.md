# git安装

因为git是一个分布式管理系统是要和其他人一起用的所以要有一个对应着自己这台机器的身份信息，也就是名字和邮箱

```
ASUS@DESKTOP-IVAJA2P MINGW64 /
$ git config --global user.name "caohao"

ASUS@DESKTOP-IVAJA2P MINGW64 /
$ git config --global user.email "*@qq.com"
```

执行完成之后会在用户目录中多出一个.gitconfig文件

## 创建版本库

bash打开的git窗口的命令和Linux基本一样

//创建仓库

mkdir 路径

上面只是创建了一个repository

下面要把它变为仓库

打开目录

cd 目录

执行仓库初始化命令

git init

初始化完成后会有一个.git的隐藏文件夹这个就和svn一样

# 文件管理

## 文件添加

git的文件管理只能管理文本文件的变动这类，比如程序代码，txt, md这类的文件，注意的是world是不能跟踪的

下面我们建立一个readme文件测试一下各种git命令

```

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ touch readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ ll
total 0
-rw-r--r-- 1 ASUS 197609 0 4月   2 11:23 readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ vi readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ cat readme.txt
这是学习git时测试用的

```

**git ststus 命令 可以看到没有readme没有提交这个命令可以看到我们暂存区和工作区中的文件**

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git status
On branch master

Initial commit

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        readme.txt

nothing added to commit but untracked files present (use "git add" to track)

```

**git add *** 

```

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git add readme.txt
warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git status
On branch master

Initial commit

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)

        new file:   readme.txt

```

此时这个文件进入了我们的暂存区

**git commit -m  把暂存区的文件提交到分支中 -m可以写一些描述信息和我们在idea中使用git时候是一个意思**

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git commit -m "学习git命令的测试"
[master (root-commit) aab095b] 学习git命令的测试
warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.
 1 file changed, 1 insertion(+)
 create mode 100644 readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git status
On branch master
nothing to commit, working directory clean

```



git分为工作区和版本库，版本库中有包含了暂存区和分支，我们刚刚新建的readme文件一开始是放在工作区中，而后我们执行了add将readme文件交给git管理提交到了暂存区，而我们commit之后就将暂存区的全部文件提交到了分支中。

**git diff * 查看目标文件与分支中这个文件的区别，如果没有区别就不会显示东西**

也就是如果我们修改了某个已经提交过的文件那么通过diff可以看到这个文件都修改了哪些地方，之后的操作和上面一样add,commit到分支下面演示一下：

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git diff readme.txt
diff --git a/readme.txt b/readme.txt
index bf9cb75..358ef18 100644
--- a/readme.txt
+++ b/readme.txt
@@ -1 +1,2 @@
 这是学习git时测试用的
+这是diff命令测试
warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git add readme.txt
warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git commit -m "diff测试"
[master warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.
feed555] diff测试
warning: LF will be replaced by CRLF in readme.txt.
The file will have its original line endings in your working directory.
 1 file changed, 1 insertion(+)

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git diff


```

## 文件撤销和版本回退

**git log 指定文件名 可以看到指定文件在当前分支的提交记录或者说是版本记录**

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git log readme.txt
commit feed5550a69926d84af52961f1471416554e4932
Author: caohao <1224819118@qq.com>
Date:   Thu Apr 2 11:42:44 2020 +0800

    diff测试

commit aab095b6478a158952262fdf60a6de6b69098d17
Author: caohao <1224819118@qq.com>
Date:   Thu Apr 2 11:33:28 2020 +0800

    学习git命令的测试

```

### 撤销修改

因为我们的git分为三个区域那么撤销修改也应该会有三种情况

工作区

文件只是在工作区，我们只需要撤销修改就可以了，不存在版本问题，因为这个文件新的修改还没有交给git管理只是在工作区中

**git checkout filename 撤销指定文件的修改**

暂存区

先把文件修改一下并加到暂存区

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ vi readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ cat readme.txt
这是学习git时测试用的
这是diff命令测试
文件在暂存区中回退测试

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git add readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   readme.txt

```

文件在暂存区

**git reset filename** 

**git checkout filename 撤销指定文件的修改**

通过依次执行以上两个命令撤销

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git checkout readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ cat readme.txt
这是学习git时测试用的
这是diff命令测试
文件在暂存区中回退测试

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git reset readme.txt
Unstaged changes after reset:
M       readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git checkout readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ cat readme.txt
这是学习git时测试用的
这是diff命令测试

```

### 版本回退

分支

首先将修改加入到分支中

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ vi readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git add readme.txt

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git commit
Aborting commit due to empty commit message.

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git commit -m '版本回退测是'
[master 44b90bc] 版本回退测是
 1 file changed, 1 insertion(+)

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git status
On branch master
nothing to commit, working directory clean

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git log readme.txt
commit 44b90bce0cd624eca25cce417a2e0706f3219700
Author: caohao <1224819118@qq.com>
Date:   Thu Apr 2 12:01:13 2020 +0800

    版本回退测是

commit feed5550a69926d84af52961f1471416554e4932
Author: caohao <1224819118@qq.com>
Date:   Thu Apr 2 11:42:44 2020 +0800

    diff测试

commit aab095b6478a158952262fdf60a6de6b69098d17
Author: caohao <1224819118@qq.com>
Date:   Thu Apr 2 11:33:28 2020 +0800

    学习git命令的测试

```

文件已经提交到分支中了

通过log我们可以看到44b90bce0cd624eca25cce417a2e0706f3219700这样的uuid这个就是版本号

**git reset  --hard HEAD^ 这个命令可以回到上一个版本**

```

ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ git reset --hard HEAD^
HEAD is now at feed555 diff测试

```

**git reset  --hard  版本号 这个命令可以回到指定版本**

### 文件删除

**1.git rm -rf filename 这样就可以删除本地文件并且将删除的结果提交到暂存区，只要我们在重新commit一次就可以了**

# 分支管理

git的分支就是一条线，上面进行回退时使用的head就是指向当前分支，而我们之前所操作的都是在master这个主分支上的，我们可以在这个分支上开辟一个新的分支比如dev分支。git的分支是通过指针来表明所在分支的，新的分支不过是在当前分支上复制并生成一个新的指针指向赋值后的分支即可所有git的分支创建很快，而且git的分支合并也很快，只要调整指针的指向即可比如将master指向我们后创建的dev就可以将dev与master进行合并了。

**git branch dev 创建了一个dev分支**

**git branch 查看所有分支**

**git checkout -b dev 在当前仓库创建一个dev分支并切换**

**git checkout dev 切换到dev分支**

**git branch -d dev 删除dev分支 注意删除时应该离开这个分支在删除**

**git merge dev 将dev合并到当前分支**

如果合并时基础版本不一样要手动合并一下

在实际开发中最好从主分支上切出一个dev分支而具体的程序人员将各自的分支合并到dev上再由dev上测试无误在发布到master上master上方的都是稳定版本

# GitHub远程仓库

首先去注册个账号

在自己的电脑上生成sshkey

```
ASUS@DESKTOP-IVAJA2P MINGW64 /F/gitRepository/hellow (master)
$ ssh-keygen -t rsa -C "1224819118@qq.com"
Generating public/private rsa key pair.
Enter file in which to save the key (/c/Users/ASUS/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /c/Users/ASUS/.ssh/id_rsa.
Your public key has been saved in /c/Users/ASUS/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:bLqdIPGvYkPwJykkkS9WCNhW/h7AzZEPxbXdXnhCtwo 1224819118@qq.com
The key's randomart image is:
+---[RSA 2048]----+
|+o... .+...   . .|
|+.o+ oo..  o o o.|
| +. + oo  .E. +.o|
|o.+  o ..   ...+ |
|.+ o..o S    ..  |
|  . =+.+         |
|   o.o=          |
|    +. = .       |
|   . oo.+        |
+----[SHA256]-----+

```

执行成功会在用户目录下的,ssh文件夹下会有一个公钥一个私钥id_rsa和id_rsa.pub

在我们GitHub上的setting中的sshkey中设置你的公钥就可以了

设置了这个就可以限制只有设置在这里面的key的用户才可以推送

origin的意思就是默认的远程仓库名，没必要改

**git remote add  origin 地址  这样就可以把本地和远程仓库关联起来**

**git push -u origin 分支名**

**git clone 地址** 

克隆下来你会发现你这边并不能显示除了默认以外的分支

**git checkout -b dev origin/dev 关联dev分支**

# 多人协作

**git remote -v 可以看到自己对于这个仓库的权限**

# gitee远程仓库

码云的基本步骤和GitHub是差不多的也时设置公钥