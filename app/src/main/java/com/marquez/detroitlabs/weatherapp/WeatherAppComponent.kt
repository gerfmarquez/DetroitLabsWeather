package com.marquez.detroitlabs.weatherapp


import com.marquez.detroitlabs.weatherapp.location.LocationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


import dagger.android.AndroidInjector

import javax.inject.Singleton

@Singleton
@Component( modules = [AndroidInjectionModule::class,WeatherAppModule::class,  LocationModule::class])
interface WeatherAppComponent : AndroidInjector<WeatherApp> {

        @Component.Builder
        interface Builder {
                @BindsInstance
                fun application(application: WeatherApp): Builder

                fun build(): WeatherAppComponent
        }

        override fun inject(app: WeatherApp)
}