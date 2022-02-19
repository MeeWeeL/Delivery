package com.meeweel.delivery.di

import androidx.room.Room
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.repository.network.RemoteDataSourceImpl
import com.meeweel.delivery.repository.DataSource
import com.meeweel.delivery.repository.Repository
import com.meeweel.delivery.repository.RepositoryImpl
import com.meeweel.delivery.repository.room.DBStorage
import com.meeweel.delivery.repository.room.LocalDataSourceImpl
import com.meeweel.delivery.ui.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NAME_REMOTE = "Name_remote"
const val NAME_LOCAL = "Name_local"

val application = module {
    single { Room.databaseBuilder(get(), DBStorage::class.java, "EntityDB").build() }
    single<DataSource<List<DataModel>>>(named(NAME_REMOTE)) { RemoteDataSourceImpl() }
    single<DataSource<List<DataModel>>>(named(NAME_LOCAL)) {
        LocalDataSourceImpl(get())
    }
}

val mainScreen = module {
    factory<Repository<List<DataModel>>> {
        RepositoryImpl(
            get(named(NAME_REMOTE)),
            get(named(NAME_LOCAL))
        )
    }
    factory { MainViewModel(get()) }
}