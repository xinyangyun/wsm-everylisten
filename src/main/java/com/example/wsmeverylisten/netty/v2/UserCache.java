package com.example.wsmeverylisten.netty.v2;

import io.netty.util.Recycler;

public class UserCache {

       private static final Recycler<User> userRecycle = new Recycler<User>() {
           @Override
           protected User newObject(Handle<User> handle) {
               return new User(handle);
           }
       };



    static final class User{
        private String name;
        private Recycler.Handle<User> handle;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Recycler.Handle<User> getHandle() {
            return handle;
        }

        public void setHandle(Recycler.Handle<User> handle) {
            this.handle = handle;
        }

        public User(Recycler.Handle<User> handle) {
            this.handle = handle;
        }

        public void recycle() {
            handle.recycle(this);
        }

    }

    public static void main(String[] args) {
        User user = userRecycle.get();//1.从对象池获取user对象
        user.setName("hello");//2.设置user对象属性
        user.recycle();//3.回收对象到对象池
        User user1 = userRecycle.get();//4.从对象池获取对象
        System.out.println(user1.getName());
        System.out.println(user == user1);


    }

}



