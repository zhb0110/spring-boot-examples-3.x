## spring-boot-scheduler-tasks

1. springboot注解方式schedule：关于@EnableScheduling+@Scheduled简单使用
2. java原生，使用ScheduledExecutorService
3. java原生，Timer的简单使用，少用

## 2 java原生ScheduledExecutorService:schedule 和 scheduleAtFixedRate和 scheduleWithFixedDelay有何区别？

● schedule：延迟执行一个任务。
● scheduleAtFixedRate：每次执行时间为上一次任务开始起向后推一个period间隔，也就是说下次执行时间相对于上一次任务开始的时间点；按照上述的例子，它保证了总时间段内的任务的执行次数
● scheduleAtFixedDelay：每次执行完当前任务后，然后间隔一个period的时间再执行下一个任务；
当某个任务执行周期大于时间间隔时，依然按照间隔时间执行下个任务，即它保证了任务之间执行的间隔。
（PS：和timer对比下，timer中没有scheduleAtFixedDelay，它的schedule等同于scheduleAtFixedDelay）

## 3 java原生Timer

### schedule 和 scheduleAtFixedRate 有何区别？

● schedule：每次执行完当前任务后，然后间隔一个period的时间再执行下一个任务；
当某个任务执行周期大于时间间隔时，依然按照间隔时间执行下个任务，即它保证了任务之间执行的间隔。
● scheduleAtFixedRate：每次执行时间为上一次任务开始起向后推一个period间隔，也就是说下次执行时间相对于上一次任务开始的时间点；按照上述的例子，它保证了总时间段内的任务的执行次数

### 为什么几乎很少使用Timer这种方式？

Timer底层是使用一个单线来实现多个Timer任务处理的，所有任务都是由同一个线程来调度，所有任务都是串行执行，意味着同一时间只能有一个任务得到执行，而前一个任务的延迟或者异常会影响到之后的任务。
如果有一个定时任务在运行时，产生未处理的异常，那么当前这个线程就会停止，那么所有的定时任务都会停止，受到影响。
PS：在这点上你可以看到，定时任务Job中异常和超时等一般都是要自行处理的，以防止对其它任务的影响。

## 注意，如果是测试类，类名必须得有Test，可大小写
