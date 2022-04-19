import SwiftUI
import app

struct ContentView: View {

	let greet = Greeting().greeting()

    @State var forecastState: ForecastState = ForecastState.Initial()

	var body: some View {
        switch forecastState {
        case is ForecastState.Initial:
            LoadingView().onAppear {
                observeForecasts()
            }
        case is ForecastState.Loading:
            LoadingView()
        case is ForecastState.Loaded:
            let fullForecast = (forecastState as! ForecastState.Loaded).fullForecast
            LoadedView(fullForecast: fullForecast.forecasts)
        case is ForecastState.Failed:
            FailedView()
        default:
            Text("default")
        }
	}

   func observeForecasts() {
       let viewModel = ForecastViewModel(weatherContract: WeatherRepository())
       viewModel.forecastStateLiveData.addObserver { value in
           if let state = value {
               forecastState = state
           }
       }
       viewModel.refreshForecast()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct LoadingView: View {
    var body: some View {
        ProgressView()
    }
}

struct LoadedView: View {
    let fullForecast: [Forecast]

    var body: some View {
        List(self.fullForecast, id: \.name) { forecast in
            CardView(forecast: forecast)
        }
    }
}

struct FailedView: View {
    var body: some View {
        Text("Failed to download forecast.")
    }
}


struct CardView: View {
    let forecast: Forecast

    var body: some View {
        HStack(spacing: .zero) {
            VStack(alignment: .leading) {
                Text(forecast.name)
                    .font(.headline)
                    .accessibilityAddTraits(.isHeader)
                Text(forecast.shortForecast)
                    .font(.subheadline)
            }
            Spacer(minLength: 16)
            AsyncImage(url: URL(string: forecast.icon)) { image in
                image.resizable()
            } placeholder: {
                ProgressView()
            }
            .frame(width: 50, height: 50)
            Text("\(forecast.temperature)\(forecast.temperatureUnit)")
                .font(.headline)
        }
    }
}
