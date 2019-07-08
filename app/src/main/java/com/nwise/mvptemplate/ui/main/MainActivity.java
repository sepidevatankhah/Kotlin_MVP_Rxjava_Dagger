package com.nwise.mvptemplate.ui.main;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import com.nwise.mvptemplate.R;
import com.nwise.mvptemplate.di.components.ActivityComponent;
import com.nwise.mvptemplate.domain.models.Answer;
import com.nwise.mvptemplate.domain.models.ListWrapper;
import com.nwise.mvptemplate.domain.models.Question;
import com.nwise.mvptemplate.ui.base.BaseActivity;

/**
 * Created by Sepideh Vatankhah on 01.08.2019.
 * sun.vatankhah@gmail.com
 * https://github.com/sepidevatankhah
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainViewInterface {


    private Spinner questionsSpinner;
    private RecyclerView recyclerView;

    @Override
    protected void injectDependencies(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsSpinner = (Spinner) findViewById(R.id.questions_spinner);

        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Spinner item selected", Toast.LENGTH_LONG).show();
                Question question = (Question) parent.getAdapter().getItem(position);
                presenter.bindAnswers(question.questionId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    @Override
    public void onQuestionSucceed(ListWrapper<Question> questions) {
        ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, questions.items);
        questionsSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onAnswerSucceed(ListWrapper<Answer> answers) {
        recyclerView.setAdapter(new RecyclerViewAdapter(answers , getApplicationContext()));
    }

    @Override
    public void onFailed() {

    }
}



