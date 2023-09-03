//package com.example.wsmeverylisten.contorller;
//
//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.Funnels;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@RestController
//@Slf4j
//public class RedisController {
//
//        @Autowired
//        private StringRedisTemplate stringRedisTemplate;
//
//        private AtomicInteger atomicInteger = new AtomicInteger();
//
//        @Autowired
//        private RedissonClient redissonClient;
//
//        private BloomFilter<Integer> bloomFilter;
//
//
////        @PostConstruct
//        public void wrongInit() {
//            //初始化1000个城市数据到Redis，所有缓存数据有效期30秒
//            IntStream.rangeClosed(1, 1000).forEach(i -> stringRedisTemplate.opsForValue().set("city" + i, getCityFromDb(i), 30, TimeUnit.SECONDS));
//            log.info("Cache init finished");
//
//
//            //每秒一次，输出数据库访问的QPS
//            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//                log.info("DB QPS : {}", atomicInteger.getAndSet(0));
//            }, 0, 1, TimeUnit.SECONDS);
//
//        }
//
//        private String getCityFromDb(int cityId) {
//            //模拟查询数据库，查一次增加计数器加一
//            atomicInteger.incrementAndGet();
//            return "citydata" + System.currentTimeMillis();
//
//        }
//
//        public String right() {
//            String data = stringRedisTemplate.opsForValue().get("hotsopt");
//            if (StringUtils.isEmpty(data)) {
//                RLock locker = redissonClient.getLock("locker");
//                //获取分布式锁
//                if (locker.tryLock()) {
//
//                    try {
//                        data = stringRedisTemplate.opsForValue().get("hotsopt");
//                        //双重检查，因为可能已经有一个B线程过了第一次判断，在等锁，然后A线程已经把数据写入了Redis中
//                        if (StringUtils.isEmpty(data)) {
//                            //回源到数据库查询
//    //                        data = getExpensiveData();
//                            data = "testx";
//                            stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
//                        }
//                    } finally {
//                        //别忘记释放，另外注意写法，获取锁后整段代码try+finally，确保unlock万无一失
//                        locker.unlock();
//                    }
//                }
//            }
//
//            return data;
//        }
//
//
////    @PostConstruct
//    public void init() {
//        //创建布隆过滤器，元素数量10000，期望误判率1%
//        bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 10000, 0.01);
//
//        //填充布隆过滤器
//        IntStream.rangeClosed(1, 10000).forEach(bloomFilter::put);
//
//    }
//
//    @GetMapping("right2")
//    public String right2(@RequestParam("id") int id) {
//        String data = "";
//        //通过布隆过滤器先判断
//        if (bloomFilter.mightContain(id)) {
//            String key = "user" + id;
//            //走缓存查询
//            data = stringRedisTemplate.opsForValue().get(key);
//            if (StringUtils.isEmpty(data)) {
//                //走数据库查询
//                data = getCityFromDb(id);
//                stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
//            }
//        }
//
//        return data;
//
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        //启动10个线程
//
//        IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
//
//            while (true) {
//
//                //每一个线程都是一个死循环，休眠10秒，打印10M数据
//
//                String payload = IntStream.rangeClosed(1, 10000000)
//
//                        .mapToObj(__ -> "a")
//
//                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
//
//                try {
//
//                    TimeUnit.SECONDS.sleep(10);
//
//                } catch (InterruptedException e) {
//
//                    e.printStackTrace();
//
//                }
//
//                System.out.println(payload.length());
//
//            }
//
//        })).forEach(Thread::start);
//
//
//
//        TimeUnit.HOURS.sleep(1);
//    }
//
//
//
//}
