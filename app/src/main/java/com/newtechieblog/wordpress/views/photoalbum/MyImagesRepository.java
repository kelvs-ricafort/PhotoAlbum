package com.newtechieblog.wordpress.views.photoalbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyImagesRepository {
    private MyImagesDAO myImagesDAO;
    private LiveData<List<MyImages>> imagesList;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MyImagesRepository(Application application) {
        MyImagesDatabase database = MyImagesDatabase.getInstance(application);
        myImagesDAO = database.myImagesDAO();
        imagesList = myImagesDAO.getAllImages();
    }

    public void insert(MyImages myImages) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               myImagesDAO.insert(myImages);
            }
        });
    }

    public void delete(MyImages myImages) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myImagesDAO.delete(myImages);
            }
        });
    }

    public void update(MyImages myImages) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myImagesDAO.update(myImages);
            }
        });
    }
    public LiveData<List<MyImages>> getAllImages() {
        return imagesList;
    }
}
