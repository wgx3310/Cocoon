package com.reid.cocoon.common.content;

/**
 * Created by reid on 2017/8/27.
 */

public class Constant {


    public static final String UNSPLASH_CLIENT_ID = "eddf9dcb1dc1969acd0ce60ded64f84c56a2e382c59bb97a6a5e7b177483f1e6";
//    public static final String UNSPLASH_CLIENT_ID = "059180b101e10fc1cb00bf9f434a6032aa0629702e3e363c2516effd05f3192f";
    public static final String UNSPLASH_CLIENT_SECRET = "b9f7b47896202e7f608f524f81d6c4303b900ada4512676d7d2cc324b57cc8d8";
    public static final String UNSPLASH_CALLBACK_URL = "http://cocoon.reid.com/auth";
    public static final String UNSPLASH_OAUTH_URL = "https://unsplash.com/oauth/authorize?client_id="+ UNSPLASH_CLIENT_ID+"&redirect_uri=http%3A%2F%2Fcocoon.reid.com%2Fauth&response_type=code&scope=public+read_user+write_user+read_photos+write_photos+write_likes+write_followers+read_collections+write_collections";


    public static final String BASE_URL_UNSPLASH_API = "https://api.unsplash.com/";
}
