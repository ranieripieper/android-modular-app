package me.ranieripieper.android.github.core.di.module;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> map;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelMap) {
        this.map = viewModelMap;
    }

    @SuppressWarnings(value = {"unckecked"})
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> provider = map.get(modelClass);
        if (provider == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : map.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    provider = entry.getValue();
                    break;
                }
            }
        }

        if (provider == null) {
            throw new IllegalArgumentException("Classe " + modelClass + "desconhecida!");
        }

        return (T) provider.get();

    }
}
