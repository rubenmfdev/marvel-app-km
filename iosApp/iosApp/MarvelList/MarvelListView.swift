import SwiftUI
import shared

struct MarvelListView: View {
    @ObservedObject private var stateHolder = MarvelListStateHolder()
    
    var body: some View {
        NavigationView {
            Group {
                switch stateHolder.state {
                case .loading:
                    ProgressView("Loading...")
                case .success(let characters):
                    List {
                        ForEach(characters, id: \.id) { character in
                            VStack(alignment: .leading) {
                                Text(character.name).font(.headline)
                                if !character.description.isEmpty {
                                    Text(character.description).font(.subheadline)
                                }
                            }
                            .padding(.vertical, 4)
                            .onAppear {
                                if character == characters.last {
                                    stateHolder.onEndReached()
                                }
                            }
                        }
                    }
                    .refreshable {
                        stateHolder.onPullToRefresh()
                    }
                case .error(let message):
                    VStack {
                        Text("Error: \(message)").foregroundColor(.red)
                        Button("Retry") {
                            stateHolder.onPullToRefresh()
                        }
                        .padding(.top, 8)
                    }
                }
            }
            .navigationTitle("Marvel Characters")
        }
        .onAppear {
            stateHolder.start()
        }
    }
}
