package com.ou.configs;

import com.cloudinary.Cloudinary;
import com.ou.pojos.*;
import com.ou.utils.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Configuration
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:momo.properties")
@PropertySource("classpath:sms.properties")
@PropertySource("classpath:cloudinary.properties")
public class BeanFactoryConfig {

    @Bean
    public UtilBeanFactory utilBeanFactory() {
        return new UtilBeanFactory();
    }

    @Bean
    public PojoBeanFactory pojoBeanFactory() {
        return new PojoBeanFactory();
    }


    public static class PojoBeanFactory implements ApplicationContextAware {
        private ApplicationContext applicationContext;

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public RoleEntity roleEntity() {
            return new RoleEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public AccountEntity accountEntity() {
            return new AccountEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public SalePercentEntity salePercentEntity() {
            return new SalePercentEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public PaymentTypeEntity paymentTypeEntity() {
            return new PaymentTypeEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public ServiceEntity serviceEntity() {
            return new ServiceEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public ScheduleEntity scheduleEntity() {
            return new ScheduleEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public NoteEntity noteEntity() {
            return new NoteEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public ServingObjectEntity servingObjectEntity() {
            return new ServingObjectEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public FeatureEntity featureEntity() {
            return new FeatureEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public StorageEntity storageEntity() {
            return new StorageEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TransportEntity transportEntity() {
            return new TransportEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public SaleEntity saleEntity() {
            return new SaleEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public DepartureDateEntity departureDateEntity() {
            return new DepartureDateEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public CategoryEntity categoryEntity() {
            return new CategoryEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public BillEntity billEntity() {
            return new BillEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public NewsEntity newsEntity() {
            return new NewsEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public PostEntity postEntity() {
            return new PostEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourEntity tourEntity() {
            return new TourEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public ThumbnailEntity thumbnailEntity() {
            return new ThumbnailEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public PostCommentEntity postCommentEntity() {
            return new PostCommentEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public NewsLikeEntity newsLikeEntity() {
            return new NewsLikeEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourRatingEntity tourRatingEntity() {
            return new TourRatingEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourServiceEntity tourServiceEntity() {
            return new TourServiceEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourNoteEntity tourNoteEntity() {
            return new TourNoteEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourServingObjectEntity tourServingObjectEntity() {
            return new TourServingObjectEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourDepartureDateEntity tourDepartureDateEntity() {
            return new TourDepartureDateEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public TourTransportEntity tourTransportEntity() {
            return new TourTransportEntity();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public BillTourServingObjectEntity billTourServingObjectEntity() {
            return new BillTourServingObjectEntity();
        }

        @Bean(name = "newsLikeEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<NewsLikeEntity> newsLikeEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "postCommentEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<PostCommentEntity> postCommentEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourDepartureDateEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourDepartureDateEntity> tourDepartureDateEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourNoteEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourNoteEntity> tourNoteEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourRatingEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourRatingEntity> tourRatingEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourServiceEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourServiceEntity> tourServiceEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourServingObjectEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourServingObjectEntity> tourServingObjectEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "tourTransportEntityList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<TourTransportEntity> tourTransportEntityList() {
            return new ArrayList<>();
        }

        @Bean(name = "billTourServingObjectEntities")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<BillTourServingObjectEntity> billTourServingObjectEntities() {
            return new ArrayList<>();
        }

        @Bean(name = "categoryEntities")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<CategoryEntity> categoryEntities() {
            return new ArrayList<>();
        }
    }


    public static class UtilBeanFactory implements ApplicationContextAware {
        private ApplicationContext applicationContext;

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        @Autowired
        public Environment env;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public PageUtil pageUtil() {
            return new PageUtil();
        }

        @Bean
        public SlugUtil slugUtil() {
            return new SlugUtil();
        }

        @Bean
        public MailUtil mailUtil() {
            return new MailUtil(env, new Properties());
        }

        @Bean
        public SMSUtil smsUtil() {
            return new SMSUtil(env);
        }

        @Bean
        public MomoUtil momoUtil() {
            return new MomoUtil();
        }

        @Bean
        public Cloudinary cloudinary() {
            Map<String, Object> configs = new HashMap<>();
            configs.put("cloud_name", env.getProperty("cloudinary.cloud_name"));
            configs.put("api_key", env.getProperty("cloudinary.api_key"));
            configs.put("api_secret", env.getProperty("cloudinary.api_secret"));
            configs.put("secure", env.getProperty("cloudinary.secure"));
            return new Cloudinary(configs);
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public JSONArray jsonArray() {
            return new JSONArray();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public JSONObject jsonObject() {
            return new JSONObject();
        }

        @Bean
        public SimpleDateFormat simpleDateFormat() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public Timestamp timestamp() {
            return new Timestamp(System.currentTimeMillis());
        }


        @Bean(name = "predicateList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<Predicate> predicateList() {
            return new ArrayList<>();
        }

        @Bean(name = "predicateArray")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public Predicate[] predicateArray() {
            return new Predicate[]{};
        }


        @Bean(name = "atomicReference")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public AtomicReference<Integer> atomicReference() {
            return new AtomicReference<>(0);
        }

        @Bean(name = "stringList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<String> stringList() {
            return new ArrayList<>();
        }

        @Bean(name = "slugList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<String> slugList() {
            return new ArrayList<>();
        }

        @Bean(name = "numberList")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public List<Integer> numberList() {
            return new ArrayList<>();
        }

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public BigDecimal bigDecimal() {
            return new BigDecimal(0);
        }


    }

}
