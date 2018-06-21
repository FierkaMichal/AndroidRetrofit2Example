package fierka.com.timlab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private NumbersListAdapter adapter;
    private List<Integer> numbers = new ArrayList<>();
    private EditText numbersCountEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupList();
    }

    private void setupList() {
        Button getButton = findViewById(R.id.input_button);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });
        numbersCountEt = findViewById(R.id.input_number_count);

        RecyclerView recyclerView = findViewById(R.id.numbers_list_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NumbersListAdapter(numbers);
        recyclerView.setAdapter(adapter);
    }

    public void onButtonClick() {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        Integer numberCount = Integer.valueOf(numbersCountEt.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RandomService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        RandomService randomService = retrofit.create(RandomService.class);
        Call<List<Integer>> call = randomService.getRandom(numberCount);

        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                numbers.clear();
                for (int i = 0; i < response.body().size(); i++) {
                    numbers.add(response.body().get(i));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
            }
        });
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        return okClient;
    }
}


//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//builder.connectTimeout(30, TimeUnit.SECONDS);
//        builder.readTimeout(30, TimeUnit.SECONDS);
//        builder.writeTimeout(30, TimeUnit.SECONDS);
//        client = builder.build();