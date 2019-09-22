package com.expence.adnanmac.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.Util;

public class MainActivity extends AppCompatActivity {
    EditText name, id;
    Button insert;
    ListView listView;
    Realm realm;
    ArrayList<String> list;
    ArrayAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
        casts();
        refresh();
    }

    private void inits() {

        realm = Realm.getDefaultInstance();
    }

    private void casts() {
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adaptor = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adaptor);
        name = (EditText) findViewById(R.id.name);
        id = (EditText) findViewById(R.id.id);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!name.getText().toString().isEmpty() && !id.getText().toString().isEmpty()) {
                    add();
                } else {
                    Toast.makeText(MainActivity.this, "Enter Data", Toast.LENGTH_LONG).show();
                }


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                delete(position);
            }
        });
    }

    public void add() {
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              User user = new User();
                                              user.setName(name.getText().toString());
                                              user.setId(id.getText().toString());
                                              realm.copyToRealmOrUpdate(user);

                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                              Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                                              name.setText("");
                                              id.setText("");
                                              refresh();
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                              Toast.makeText(MainActivity.this, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                                          }
                                      }


        );

    }

    public void refresh() {
        list.clear();
        adaptor.notifyDataSetChanged();
        RealmResults<User> result = realm.where(User.class).findAllAsync();
        result.load();
        for (User u : result) {
            list.add(u.getName());
            adaptor.notifyDataSetChanged();
        }
    }

    public void delete(final int pos) {
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              final RealmResults<User> result = realm.where(User.class).equalTo("name", list.get(pos)).findAll();
                                              result.deleteFromRealm(0);

                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                              Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                                              refresh();
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                              Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                          }
                                      }
        );
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
