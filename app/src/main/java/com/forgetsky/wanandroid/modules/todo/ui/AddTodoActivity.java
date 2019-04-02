package com.forgetsky.wanandroid.modules.todo.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.contract.AddTodoContract;
import com.forgetsky.wanandroid.modules.todo.presenter.AddTodoPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

import static com.forgetsky.wanandroid.core.constant.Constants.TODO_TYPE_ALL;
import static com.forgetsky.wanandroid.core.constant.Constants.TODO_TYPE_LIFE;
import static com.forgetsky.wanandroid.core.constant.Constants.TODO_TYPE_OTHER;
import static com.forgetsky.wanandroid.core.constant.Constants.TODO_TYPE_STUDY;
import static com.forgetsky.wanandroid.core.constant.Constants.TODO_TYPE_WORK;

/**
 * Created by ForgetSky on 2019/3/31.
 */
public class AddTodoActivity extends BaseActivity<AddTodoPresenter> implements AddTodoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.et_add_todo_title)
    EditText mAddTodoTitle;
    @BindView(R.id.et_add_todo_content)
    EditText mAddTodoContent;
    @BindView(R.id.rg_todo_priority)
    RadioGroup mPriorityRg;
    @BindView(R.id.rb_todo_priority_1)
    RadioButton mTodoPriority1;
    @BindView(R.id.rb_todo_priority_2)
    RadioButton mTodoPriority2;
    @BindView(R.id.tv_add_todo_label_content)
    TextView mAddTodoLabel;
    @BindView(R.id.tv_add_todo_date_content)
    TextView mAddTodoDate;

    private SparseArray<String> mTodoLabelArray = new SparseArray<>(5);
    String choiceLabel;
    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        mTodoLabelArray.put(TODO_TYPE_ALL, getString(R.string.todo_no_label));
        mTodoLabelArray.put(TODO_TYPE_WORK, getString(R.string.todo_work));
        mTodoLabelArray.put(TODO_TYPE_STUDY, getString(R.string.todo_study));
        mTodoLabelArray.put(TODO_TYPE_LIFE, getString(R.string.todo_life));
        mTodoLabelArray.put(TODO_TYPE_OTHER, getString(R.string.todo_other));

        TodoItemData todoItemData = (TodoItemData) getIntent().getSerializableExtra(Constants.TODO_DATA);
        if (todoItemData != null) {
            mTitle.setText(R.string.todo_edit_title);
            mAddTodoTitle.setText(todoItemData.getTitle());
            mAddTodoContent.setText(todoItemData.getContent());
            if (todoItemData.getPriority() == 1) {
                mTodoPriority1.setChecked(true);
                mTodoPriority2.setChecked(false);
            } else {
                mTodoPriority1.setChecked(false);
                mTodoPriority2.setChecked(true);
            }

            mAddTodoLabel.setText(mTodoLabelArray.get(todoItemData.getType()));

            if (TextUtils.isEmpty(todoItemData.getCompleteDateStr())) {
                mAddTodoDate.setText(todoItemData.getDateStr());
            } else {
                mAddTodoDate.setText(todoItemData.getCompleteDateStr());
            }

        } else {
            mTitle.setText(R.string.todo_new_title);
            mAddTodoLabel.setText(R.string.todo_no_label);
            mAddTodoDate.setText(CommonUtils.getCurrentDate());
        }
    }

    @OnClick({R.id.tv_add_todo_label_content, R.id.tv_add_todo_date_content,
            R.id.iv_label_arrow_right, R.id.iv_date_arrow_right,
            R.id.bt_todo_save})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_todo_label_content:
            case R.id.iv_label_arrow_right:
                choiceLabel = mAddTodoLabel.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.bt_choose_label);
                builder.setSingleChoiceItems(R.array.todo_labels,
                        mTodoLabelArray.indexOfValue(choiceLabel),
                        (dialog, which) -> choiceLabel = mTodoLabelArray.get(which));
                builder.setPositiveButton(R.string.ok,
                        (dialog, which) -> mAddTodoLabel.setText(choiceLabel));
                builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                });
                AlertDialog alertDialog = builder.show();
                break;
            case R.id.tv_add_todo_date_content:
            case R.id.iv_date_arrow_right:
                Calendar calendar = CommonUtils.dateString2Calendar(mAddTodoDate.getText().toString());
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mAddTodoDate.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.bt_todo_save:
                ToastUtils.showToast(this, getString(R.string.in_the_process));
                break;
            default:
                break;
        }
    }

}
