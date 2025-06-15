package com.dicoding.latihan_praktikum_10.presentation.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.dicoding.latihan_praktikum_10.R;
import com.dicoding.latihan_praktikum_10.data.database.BookDatabase;
import com.dicoding.latihan_praktikum_10.data.network.RetrofitClient;
import com.dicoding.latihan_praktikum_10.data.repository.BookRepositorylmpl;
import com.dicoding.latihan_praktikum_10.domain.repository.BookRepository;
import com.dicoding.latihan_praktikum_10.presentation.adapter.BookAdapter;
import com.dicoding.latihan_praktikum_10.presentation.viewmodel.BookViewModel;
import com.dicoding.latihan_praktikum_10.presentation.viewmodel.BookViewModelFactory;

import java.util.ArrayList;

public class KontentFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konten, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_konten);
        searchView = view.findViewById(R.id.search_view);
        adapter = new BookAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // 🔥 Inisialisasi viewModel
        BookRepository repository = new BookRepositorylmpl(
                RetrofitClient.getApiService(),
                BookDatabase.getInstance(requireContext()).bookDao()
        );

        BookViewModelFactory factory = new BookViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(BookViewModel.class);

        // 🔁 Ambil semua data saat awal
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            adapter.setData(books);
        });

        // 🔍 Fitur pencarian
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchBooks(query).observe(getViewLifecycleOwner(), books -> {
                    adapter.setData(books);
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
}

