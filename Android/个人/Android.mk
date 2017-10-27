
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
#LOCAL_MODULE表示生成的库的名字，前面的lib和后缀名不用写 
LOCAL_MODULE    := NDK_01
LOCAL_SRC_FILES := \HelloWorld.c

include $(BUILD_SHARED_LIBRARY)

#一个Android.mk file首先必须定义好LOCAL_PATH变量。
#它用于在开发树中查找源文件。在这个例子中，宏函数’my-dir’, 由编译系统提供，用于返回当前路径（即包含Android.mk file文件的目录）。
 
#2、include $( CLEAR_VARS)
#CLEAR_VARS 由编译系统提供，指定让GNU MAKEFILE为你清除许多LOCAL_XXX变量
#（例如 LOCAL_MODULE, LOCAL_SRC_FILES, LOCAL_STATIC_LIBRARIES, 等等...),除LOCAL_PATH 。
#这是必要的，因为所有的编译控制文件都在同一个GNU MAKE执行环境中，所有的变量都是全局的。
 
#3、LOCAL_MODULE :=  HcSyncml
#LOCAL_MODULE变量必须定义，以标识你在Android.mk文件中描述的每个模块。
#名称必须是唯一的，而且不包 含任何空格。注意编译系统会自动产生合适的前缀和后缀，
#换句话说，一个被命名为'HcSyncml'的共享库模块，将会生成'libHcSyncml.so'文件。
 
#4、LOCAL_C_INCLUDES := $(LOCAL_PATH)/extra_inc$(LOCAL_PATH)/main_inc
#LOCAL_C_INCLUDES 中加入所需要包含的头文件路径
 
#5、LOCAL_SRC_FILES
#LOCAL_SRC_FILES中加入源文件路径(需要编译的文件),多个文件用 ‘\’ 隔开
 
#6、LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib –llog
#表示允许打印Log
