package com.forgetsky.wanandroid.core.http.interceptor;

import com.forgetsky.wanandroid.utils.CommonUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Cache with Offline
 * Created by ForgetSky on 2019/3/17.
 */
public class OfflineCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!CommonUtils.isNetworkConnected()) {
            int offlineCacheTime = 60 * 60 * 24 * 28;//离线的时候的缓存的过期时间,4周
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .removeHeader("Pragma")
                    .build();
        }
        return chain.proceed(request);
    }
}
