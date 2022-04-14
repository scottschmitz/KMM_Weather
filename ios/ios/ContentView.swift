import SwiftUI
import app

struct ContentView: View {

	let greet = Greeting().greeting()

    @State var forcastDetails: String = ""

	var body: some View {
		Text("\(forcastDetails)")
           .onAppear {
               self.forcastDetails = greet
               getForecasts()
           }
	}

   func getForecasts() {
       WeatherRepository().getFullForecast { forecast, error in
           guard let forecast = forecast else {
               return
           }
           self.forcastDetails = forecast.forecasts.first!.detailedForecast
       }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
