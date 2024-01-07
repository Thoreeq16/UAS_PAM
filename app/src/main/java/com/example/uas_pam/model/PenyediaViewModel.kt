package com.example.uas_pam.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            TanamanEntryViewModel(aplikasiGreenGuardian().container.repositoriTanaman)
        }

        initializer {
            DetailViewModel(createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa,
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa,
            )
        }
    }
}
fun CreationExtras.aplikasiGreenGuardian(): AplikasiGreenGuardian = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiGreenGuardian)