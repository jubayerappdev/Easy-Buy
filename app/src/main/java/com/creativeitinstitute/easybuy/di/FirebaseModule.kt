package com.creativeitinstitute.easybuy.di

import com.creativeitinstitute.easybuy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()

    }
    @Provides
    @Singleton
    fun providesFirebaseFireStoreBD(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()

    }


    @Provides
    @Singleton
    fun providesFirebase(jAuth: FirebaseAuth, db:FirebaseFirestore): AuthRepository{
        return AuthRepository(jAuth,db)

    }
}