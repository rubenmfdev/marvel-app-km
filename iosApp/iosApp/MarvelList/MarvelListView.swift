import SwiftUI
import shared

struct MarvelListView: View {
    @StateObject private var stateHolder = MarvelListStateHolder()

    var body: some View {
        NavigationView {
            content
                .navigationTitle("Marvel Characters")
        }
    }

    @ViewBuilder
    private var content: some View {
        switch stateHolder.state {
        case .loading:
            ProgressView("Loading...")
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)

        case .success(let characters):
            List {
                ForEach(characters, id: \.id) { character in
                    VStack(alignment: .leading, spacing: 4) {
                        Text(character.name)
                            .font(.headline)

                        if !character.characterDescription.isEmpty {
                            Text(character.characterDescription)
                                .font(.subheadline)
                                .foregroundColor(.secondary)
                        }
                    }
                    .padding(.vertical, 6)
                    .onAppear {
                        if character == characters.last {
                            stateHolder.onEndReached()
                        }
                    }
                }
            }
            .listStyle(.plain)
            .refreshable {
                stateHolder.onPullToRefresh()
            }

        case .error(let message):
            VStack(spacing: 12) {
                Text("Error: \(message)")
                    .foregroundColor(.red)
                    .multilineTextAlignment(.center)

                Button("Retry") {
                    stateHolder.onPullToRefresh()
                }
                .buttonStyle(.borderedProminent)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
            .padding()
        }
    }
}
