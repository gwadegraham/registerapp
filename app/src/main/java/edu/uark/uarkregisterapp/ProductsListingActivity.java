package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ProductsListingActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products_listing);
		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		//getting the transactionTransition object from the previous intent
		this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction_transition));

		ActionBar actionBar = this.getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		this.products = new ArrayList<>();
		this.productListAdapter = new ProductListAdapter(this, this.products);

		this.getProductsListView().setAdapter(this.productListAdapter);

		this.getProductsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);

				intent.putExtra(
					getString(R.string.intent_extra_product),
					new ProductTransition((Product) getProductsListView().getItemAtPosition(position))
				);

				//putting TransactionTransition object as extra on intent
				intent.putExtra(
						getString(R.string.intent_extra_transaction_transition),
						transactionTransition
				);


				startActivity(intent);
			}
		});

		this.loadingProductsAlert = new AlertDialog.Builder(this).
			setMessage(R.string.alert_dialog_products_loading).
			create();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:  // Respond to the action bar's Up/Home button
				Intent upIntent = NavUtils.getParentActivityIntent(this);

				upIntent.putExtra(getString(R.string.intent_extra_transaction_transition), this.transactionTransition);

				if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
					TaskStackBuilder.create(this).
							addNextIntentWithParentStack(upIntent).
							startActivities();
				} else {
					NavUtils.navigateUpTo(this, upIntent);
				}

				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();

		this.loadingProductsAlert.show();
		(new RetrieveProductsTask()).execute();
	}

	private ListView getProductsListView() {
		return (ListView) this.findViewById(R.id.list_view_products);
	}

	private class RetrieveProductsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			products.clear();
			products.addAll(
				(new ProductService()).getProducts()
			);

			return null;
		}

		@Override
		protected void onPostExecute(Void param) {
			productListAdapter.notifyDataSetChanged();
			loadingProductsAlert.dismiss();
		}
	}

	private List<Product> products;
	private AlertDialog loadingProductsAlert;
	private ProductListAdapter productListAdapter;
	private TransactionTransition transactionTransition;
}
