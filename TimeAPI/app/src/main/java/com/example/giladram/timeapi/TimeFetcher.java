package com.example.giladram.timeapi;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

    public class TimeFetcher {
        private RequestQueue _queue;
        private final static String REQUEST_URL = "http://192.168.1.2:8080/gettime/";

        public class TimeResponse {
            public boolean isError;
            public String CurrentTime;

            public TimeResponse(boolean isError, String i_currentTime) {
                this.isError = isError;
                this.CurrentTime = i_currentTime;
            }

        }

        public interface TimeResponseListener {
            public void onResponse(TimeResponse response);
        }

        public TimeFetcher(Context context) {
            _queue = Volley.newRequestQueue(context);
        }

        private TimeResponse createErrorResponse() {
            return new TimeResponse(true, null);
        }

        public void dispatchRequest(final TimeResponseListener listener) {

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                TimeResponse res = new TimeResponse(false,
                                        response.getString("currentTime"));
                                listener.onResponse(res);
                            }
                            catch (JSONException e) {
                                listener.onResponse(createErrorResponse());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onResponse(createErrorResponse());
                }
            });
            _queue.add(req);
        }
    }