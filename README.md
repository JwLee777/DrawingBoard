# 一、设计目的和内容
## 1.设计目的
1 综合运用所学的类、继承和多态的知识。<br />
2 熟悉文件读取及存储方法，了解流的装配。<br />
3 掌握 JavaFX 的 GUI 设计及事务响应机制。<br />
4 进一步掌握程序的调试方法。
## 2.设计内容
1 编程实现在面板上通过鼠标拖动绘制形状，完成一个简单的画板功能<br /> 
2 通过菜单，选择绘制图形的颜色，线性，形状等<br /> 
3 可绘制的图形样式（最少包括线、圆、矩形）和图形颜色（最少能够设为以
下 4 种颜色：黑、红、绿、蓝），图形线性至少三种。<br /> 
4 图像绘制过程不会出现图像抖动（双缓冲）<br /> 
5 图像互相重叠时，能正确显示图像<br /> 
6 当窗口重绘（如窗口最小化-重新打开）后，还能保持原来的图形。<br /> 
7 能够选中已绘制的图形，并删除之。<br />
8 能够保存已绘制的图形，下次打开时恢复原状。<br />
# 二、基本功能描述
设计简易的画板程序，支持通过菜单选择“新建”建立一个新文件，此时可以在空白图形文件上通过选择工具和颜色来绘制二维图形以及对二维图形的一些操作。可以通过菜单选择“打开”或者“保存”实现对图形文件的打开或保存，存储和读取之后图形的信息保持一致。支持创建基本类型，包括直线、矩形、椭圆、圆、多边形、以及自由图形。支持对基本元素的编辑，可对元素进行多选，可对选中的元素画笔的属性进行修改，可对选中的元素进行旋转操作。可对元素进行删除。性能要求包括涵盖多种图形样式和颜色，多种图形线性，绘制时图像不抖动，重叠时可正常显示，同时还提供多种画笔粗细选择。本软件采用javaFx编程，jdk为java1.8。
