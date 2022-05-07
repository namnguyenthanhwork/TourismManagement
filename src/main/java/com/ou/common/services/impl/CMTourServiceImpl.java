package com.ou.common.services.impl;


import com.ou.common.repositories.*;
import com.ou.common.services.CMThumbnailService;
import com.ou.common.services.CMTourService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CMTourServiceImpl implements CMTourService {
    @Autowired
    private CMTourRepository cMTourRepository;

    @Autowired
    private CMPostRepository cMPostRepository;

    @Autowired
    private CMSaleRepository cMSaleRepository;

    @Autowired
    private CMCategoryRepository cMCategoryRepository;

    @Autowired
    private CMServiceRepository cMServiceRepository;

    @Autowired
    private CMNoteRepository cMNoteRepository;

    @Autowired
    private CMServingObjectRepository cMServingObjectRepository;

    @Autowired
    private CMDepartureDateRepository cMDepartureDateRepository;

    @Autowired
    private CMTransportRepository cMTransportRepository;

    @Autowired
    private CMTourServiceRepository cMTourServiceRepository;

    @Autowired
    private CMTourNoteRepository cMTourNoteRepository;

    @Autowired
    private CMTourServingObjectRepository cMTourServingObjectRepository;

    @Autowired
    private CMTourDepartureDateRepository cMTourDepartureDateRepository;

    @Autowired
    private CMTourTransportRepository cMTourTransportRepository;

    @Autowired
    private CMScheduleRepository cMScheduleRepository;

    @Autowired
    private CMFeatureRepository cMFeatureRepository;

    @Autowired
    private CMThumbnailService cMThumbnailService;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    private void setAdditionTourInformation(JSONObject jsonObject, String tourSlug) {
        JSONArray services = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray notes = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray servingObjects = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray departureDates = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray transports = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray thumbnails = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        JSONArray schedules = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        TourEntity tour = cMTourRepository.getTour(tourSlug);
        cMTourServiceRepository.getTourServiceByTour(tourSlug).forEach(tourServiceEntity -> {
            ServiceEntity service = cMServiceRepository.getService(tourServiceEntity.getServId());
            JSONObject servJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            servJson.put("servId", service.getServId());
            servJson.put("servTitle", service.getServTitle());
            servJson.put("servSlug", service.getServSlug());
            servJson.put("servContent", service.getServContent());
            services.add(servJson);
        });

        cMTourNoteRepository.getTourNoteByTour(tourSlug).forEach(tourNoteEntity -> {
            NoteEntity note = cMNoteRepository.getNote(tourNoteEntity.getNoteId());
            JSONObject noteJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            noteJson.put("noteId", note.getNoteId());
            noteJson.put("noteTitle", note.getNoteTitle());
            noteJson.put("noteSlug", note.getNoteSlug());
            noteJson.put("noteContent", note.getNoteContent());
            notes.add(noteJson);
        });

        cMTourServingObjectRepository.getTourServingObjectByTour(tourSlug).forEach(tourServingObjectEntity -> {
            ServingObjectEntity servingObject = cMServingObjectRepository
                    .getServingObject(tourServingObjectEntity.getSvoId());
            JSONObject svoJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            svoJson.put("svoId", servingObject.getSvoId());
            svoJson.put("svoName", servingObject.getSvoName());
            svoJson.put("svoSlug", servingObject.getSvoSlug());
            svoJson.put("tourPrice", tourServingObjectEntity.getTourPrice());
            servingObjects.add(svoJson);
        });

        cMTourDepartureDateRepository.getTourDepartureDateByTour(tourSlug).forEach(tourDepartureDateEntity -> {
            DepartureDateEntity departureDateEntity = cMDepartureDateRepository
                    .getDepartureDate(tourDepartureDateEntity.getDptId());
            JSONObject dptJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            dptJson.put("dptId", departureDateEntity.getDptId());
            dptJson.put("dptDate", departureDateEntity.getDptDate());
            dptJson.put("tourAmount", tourDepartureDateEntity.getTourAmount());
            FeatureEntity feature = cMFeatureRepository.getFeature(departureDateEntity.getFeaId());
            dptJson.put("feaName", feature.getFeaName());
            dptJson.put("feaId", feature.getFeaId());
            TourDepartureDateEntity tourDepartureDate = cMTourDepartureDateRepository.getTourDepartureDateEntity(
                    tour.getTourId(), departureDateEntity.getDptId());
            dptJson.put("tourEmptySlot", tourDepartureDate.getTourAmount()-tourDepartureDate.getTourSellAmount());
            departureDates.add(dptJson);
        });

        cMTourTransportRepository.getTourTransportByTour(tourSlug).forEach(tourTransportEntity -> {
            TransportEntity transport = cMTransportRepository.getTransport(tourTransportEntity.getTranId());
            JSONObject tranJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            tranJson.put("tranId", transport.getTranId());
            tranJson.put("tranName", transport.getTranName());
            tranJson.put("tranSlug", transport.getTranSlug());
            transports.add(tranJson);
        });

        cMScheduleRepository.getSchedulesByTourId(tour.getTourId()).forEach(scheduleEntity -> {
            JSONObject scheduleJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            scheduleJson.put("scheId", scheduleEntity.getScheId());
            scheduleJson.put("scheTitle", scheduleEntity.getScheTitle());
            scheduleJson.put("scheSlug", scheduleEntity.getScheSlug());
            scheduleJson.put("scheContent", scheduleEntity.getScheContent());
            schedules.add(scheduleJson);
        });

        cMThumbnailService.getThumbnailsByTourId(tour.getTourId()).forEach(thumbnailEntity -> {
            JSONObject thumJson = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            thumJson.put("thumId", thumbnailEntity.getThumId());
            thumJson.put("thumImage", thumbnailEntity.getThumImage());
            thumbnails.add(thumJson);
        });

        jsonObject.put("services", services);
        jsonObject.put("notes", notes);
        jsonObject.put("servingObjects", servingObjects);
        jsonObject.put("departureDates", departureDates);
        jsonObject.put("transports", transports);
        jsonObject.put("schedules", schedules);
    }

    @Override
    public JSONArray getTours(Integer pageIndex, String... params) {
        List<Object[]> tours = cMTourRepository.getTours(pageIndex, params);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tours.forEach(tour -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("tourId", tour[0]);
            jsonObject.put("tourAverageRating", tour[1]);
            if(tour[2]!=null) {
                CategoryEntity category = cMCategoryRepository.getCategory((Integer) tour[2]);
                jsonObject.put("catId", category.getCatId());
                jsonObject.put("catName", category.getCatName());
                jsonObject.put("catSlug", category.getCatSlug());
            }else{
                jsonObject.put("catId", null);
                jsonObject.put("catName", null);
                jsonObject.put("catSlug", null);
            }
            if (tour[3] != null) {
                SaleEntity sale = cMSaleRepository.getSale((Integer) tour[3]);
                jsonObject.put("saleId", sale.getSaleId());
                jsonObject.put("saleFromDate", sale.getSaleFromDate());
                jsonObject.put("saleToDate", sale.getSaleToDate());
            } else {
                jsonObject.put("saleId", null);
                jsonObject.put("saleFromDate", null);
                jsonObject.put("saleToDate", null);
            }
            jsonObject.put("tourTitle", tour[4]);
            jsonObject.put("tourSlug", tour[5]);
            jsonObject.put("tourCoverPage", tour[7]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public JSONArray getTours() {
        List<Object[]> tours = cMTourRepository.getTours();
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        tours.forEach(tour->{
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("tourTitle", tour[0]);
            jsonObject.put("tourSlug", tour[1]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getTourAmount(String ... params) {
        return cMTourRepository.getTourAmount(params);
    }

    @Override
    public int getTotalTourSlot(Integer tourId) {
        if (tourId == null)
            return 0;
        Timestamp timestamp = utilBeanFactory.getApplicationContext().getBean("currentTimeStamp", Timestamp.class);
        return (int) cMTourRepository.getTotalTourSlot(tourId, timestamp);
    }

    @Override
    public int getEmptySlotAmount(Integer tourId) {
        if (tourId == null)
            return 0;
        Timestamp timestamp = utilBeanFactory.getApplicationContext().getBean("currentTimeStamp", Timestamp.class);
        long totalTourSlot = cMTourRepository.getTotalTourSlot(tourId, timestamp);
        long totalTourSellSlot = cMTourRepository.getTotalSellTourSlot(tourId, timestamp);
        return (int) (totalTourSlot - totalTourSellSlot);
    }

    @Override
    public TourEntity getTourAsObj(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        return cMTourRepository.getTour(tourSlug.trim());
    }

    @Override
    public JSONObject getTourAverageRating(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        TourEntity tour = cMTourRepository.getTour(tourSlug);
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        jsonObject.put("tourAverageRating", tour.getTourAverageRating());
        return jsonObject;
    }

    @Override
    public JSONObject getTourAsJsonObj(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        TourEntity tour = cMTourRepository.getTour(tourSlug.trim());
        if (tour == null)
            return null;
        jsonObject.put("tourId", tour.getTourId());
        jsonObject.put("tourAverageRating", tour.getTourAverageRating());
        CategoryEntity category = cMCategoryRepository.getCategory(tour.getCatId());
        jsonObject.put("catId", category.getCatId());
        jsonObject.put("catName", category.getCatName());
        jsonObject.put("catSlug", category.getCatSlug());

        if (tour.getSaleId() != null) {
            SaleEntity sale = cMSaleRepository.getSale(tour.getSaleId());
            jsonObject.put("saleId", sale.getSaleId());
            jsonObject.put("saleFromDate", sale.getSaleFromDate());
            jsonObject.put("saleToDate", sale.getSaleToDate());
        } else {
            jsonObject.put("saleId", null);
            jsonObject.put("saleFromDate", null);
            jsonObject.put("saleToDate", null);
        }
        PostEntity post = cMPostRepository.getPost(tour.getTourId());
        jsonObject.put("tourTitle", post.getPostTitle());
        jsonObject.put("tourSlug", post.getPostSlug());
        jsonObject.put("tourContent", post.getPostContent());
        jsonObject.put("tourCoverPage", post.getPostCoverPage());
        setAdditionTourInformation(jsonObject, post.getPostSlug());
        return jsonObject;
    }

    @Override
    public boolean createTour(TourEntity tourEntity) {
        tourEntity.setTourAverageRating(0);
        return cMTourRepository.createTour(tourEntity);
    }

    @Override
    public boolean updateTour(TourEntity tourEntity) {
        return cMTourRepository.updateTour(tourEntity);
    }

    @Override
    public boolean deleteTour(TourEntity tourEntity) {
        return cMTourRepository.deleteTour(tourEntity);
    }
}
