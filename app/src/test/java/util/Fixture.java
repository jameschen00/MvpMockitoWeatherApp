package util;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fixture<T> {
    private final Class<T> fixtureClass;
    private final String fixtureFile;
    private static final String CONST_CHARSET = "UTF-8";

    public Fixture(Class<T> fixtureClass, String fixtureFile) {
        this.fixtureClass = fixtureClass;
        this.fixtureFile = fixtureFile;
    }

    public T load() {
        InputStreamReader fixtureStreamReader = null;
        try {
            fixtureStreamReader = new InputStreamReader(fixtureClass.getClassLoader().getResourceAsStream(fixtureFile), CONST_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Log.e(this.getClass().getSimpleName() , Log.getStackTraceString(e));
        }
        return new Gson().fromJson(fixtureStreamReader, fixtureClass);
    }

    public <T> List<T> loadList(Class<T[]> tClass) {
        InputStream resourcesJsonArrayStream = tClass.getClassLoader().getResourceAsStream(fixtureFile);
        InputStreamReader resourcesJsonArrayStreamReader = null;
        try {
            resourcesJsonArrayStreamReader = new InputStreamReader(resourcesJsonArrayStream, CONST_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Log.e(this.getClass().getSimpleName() , Log.getStackTraceString(e));
        }
        T[] resourcesJson = new Gson().fromJson(resourcesJsonArrayStreamReader, tClass);
        ArrayList<T> resourceList = new ArrayList<>();
        List<T> resources = Arrays.asList(resourcesJson);
        resourceList.addAll(resources);
        return resourceList;
    }
}
