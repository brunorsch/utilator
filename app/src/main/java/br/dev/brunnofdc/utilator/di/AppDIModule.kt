package br.dev.brunnofdc.utilator.di

import androidx.room.Room
import br.dev.brunnofdc.utilator.data.database.UtilatorDatabase
import br.dev.brunnofdc.utilator.domain.TipoConta
import br.dev.brunnofdc.utilator.domain.service.OperacoesLeiturasService
import br.dev.brunnofdc.utilator.presentation.ResumoContaViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { (tipoConta: TipoConta) -> ResumoContaViewModel(tipoConta, get(), get()) }

    single { Room.databaseBuilder(androidContext(), UtilatorDatabase::class.java, "utilator-db").build() }
    single { get<UtilatorDatabase>().leituraDao() }

    single { OperacoesLeiturasService(get()) }
}