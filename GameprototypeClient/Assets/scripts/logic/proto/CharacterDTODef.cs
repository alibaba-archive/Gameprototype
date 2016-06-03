// Generated by ProtoGen, Version=2.4.1.555, Culture=neutral, PublicKeyToken=17b3b1f090c3ea48.  DO NOT EDIT!
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.ProtocolBuffers;
using pbc = global::Google.ProtocolBuffers.Collections;
using pbd = global::Google.ProtocolBuffers.Descriptors;
using scg = global::System.Collections.Generic;
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
public static partial class CharacterDTODef {

  #region Extension registration
  public static void RegisterAllExtensions(pb::ExtensionRegistry registry) {
  }
  #endregion
  #region Static variables
  internal static pbd::MessageDescriptor internal__static_CharacterDTOPB__Descriptor;
  internal static pb::FieldAccess.FieldAccessorTable<global::CharacterDTOPB, global::CharacterDTOPB.Builder> internal__static_CharacterDTOPB__FieldAccessorTable;
  #endregion
  #region Descriptor
  public static pbd::FileDescriptor Descriptor {
    get { return descriptor; }
  }
  private static pbd::FileDescriptor descriptor;
  
  static CharacterDTODef() {
    byte[] descriptorData = global::System.Convert.FromBase64String(
        string.Concat(
          "ChVDaGFyYWN0ZXJEVE9EZWYucHJvdG8iZAoOQ2hhcmFjdGVyRFRPUEISEQoJ", 
          "YWNjb3VudElkGAEgAigDEhAKCHBsYXllcklkGAIgAigDEg4KBnNyZXNJZBgD", 
          "IAIoAxINCgVtYXBJZBgEIAIoAxIOCgZ0ZXJtSWQYBSACKANCHQobb3JnLmdh", 
        "bWVwcm90b3R5cGUucHJvdG8uc3Jj"));
    pbd::FileDescriptor.InternalDescriptorAssigner assigner = delegate(pbd::FileDescriptor root) {
      descriptor = root;
      internal__static_CharacterDTOPB__Descriptor = Descriptor.MessageTypes[0];
      internal__static_CharacterDTOPB__FieldAccessorTable = 
          new pb::FieldAccess.FieldAccessorTable<global::CharacterDTOPB, global::CharacterDTOPB.Builder>(internal__static_CharacterDTOPB__Descriptor,
              new string[] { "AccountId", "PlayerId", "SresId", "MapId", "TermId", });
      return null;
    };
    pbd::FileDescriptor.InternalBuildGeneratedFileFrom(descriptorData,
        new pbd::FileDescriptor[] {
        }, assigner);
  }
  #endregion
  
}
#region Messages
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
public sealed partial class CharacterDTOPB : pb::GeneratedMessage<CharacterDTOPB, CharacterDTOPB.Builder> {
  private CharacterDTOPB() { }
  private static readonly CharacterDTOPB defaultInstance = new CharacterDTOPB().MakeReadOnly();
  private static readonly string[] _characterDTOPBFieldNames = new string[] { "accountId", "mapId", "playerId", "sresId", "termId" };
  private static readonly uint[] _characterDTOPBFieldTags = new uint[] { 8, 32, 16, 24, 40 };
  public static CharacterDTOPB DefaultInstance {
    get { return defaultInstance; }
  }
  
  public override CharacterDTOPB DefaultInstanceForType {
    get { return DefaultInstance; }
  }
  
  protected override CharacterDTOPB ThisMessage {
    get { return this; }
  }
  
  public static pbd::MessageDescriptor Descriptor {
    get { return global::CharacterDTODef.internal__static_CharacterDTOPB__Descriptor; }
  }
  
  protected override pb::FieldAccess.FieldAccessorTable<CharacterDTOPB, CharacterDTOPB.Builder> InternalFieldAccessors {
    get { return global::CharacterDTODef.internal__static_CharacterDTOPB__FieldAccessorTable; }
  }
  
  public const int AccountIdFieldNumber = 1;
  private bool hasAccountId;
  private long accountId_;
  public bool HasAccountId {
    get { return hasAccountId; }
  }
  public long AccountId {
    get { return accountId_; }
  }
  
  public const int PlayerIdFieldNumber = 2;
  private bool hasPlayerId;
  private long playerId_;
  public bool HasPlayerId {
    get { return hasPlayerId; }
  }
  public long PlayerId {
    get { return playerId_; }
  }
  
  public const int SresIdFieldNumber = 3;
  private bool hasSresId;
  private long sresId_;
  public bool HasSresId {
    get { return hasSresId; }
  }
  public long SresId {
    get { return sresId_; }
  }
  
  public const int MapIdFieldNumber = 4;
  private bool hasMapId;
  private long mapId_;
  public bool HasMapId {
    get { return hasMapId; }
  }
  public long MapId {
    get { return mapId_; }
  }
  
  public const int TermIdFieldNumber = 5;
  private bool hasTermId;
  private long termId_;
  public bool HasTermId {
    get { return hasTermId; }
  }
  public long TermId {
    get { return termId_; }
  }
  
  public override bool IsInitialized {
    get {
      if (!hasAccountId) return false;
      if (!hasPlayerId) return false;
      if (!hasSresId) return false;
      if (!hasMapId) return false;
      if (!hasTermId) return false;
      return true;
    }
  }
  
  public override void WriteTo(pb::ICodedOutputStream output) {
    CalcSerializedSize();
    string[] field_names = _characterDTOPBFieldNames;
    if (hasAccountId) {
      output.WriteInt64(1, field_names[0], AccountId);
    }
    if (hasPlayerId) {
      output.WriteInt64(2, field_names[2], PlayerId);
    }
    if (hasSresId) {
      output.WriteInt64(3, field_names[3], SresId);
    }
    if (hasMapId) {
      output.WriteInt64(4, field_names[1], MapId);
    }
    if (hasTermId) {
      output.WriteInt64(5, field_names[4], TermId);
    }
    UnknownFields.WriteTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public override int SerializedSize {
    get {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
      return CalcSerializedSize();
    }
  }
  
  private int CalcSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
    
    size = 0;
    if (hasAccountId) {
      size += pb::CodedOutputStream.ComputeInt64Size(1, AccountId);
    }
    if (hasPlayerId) {
      size += pb::CodedOutputStream.ComputeInt64Size(2, PlayerId);
    }
    if (hasSresId) {
      size += pb::CodedOutputStream.ComputeInt64Size(3, SresId);
    }
    if (hasMapId) {
      size += pb::CodedOutputStream.ComputeInt64Size(4, MapId);
    }
    if (hasTermId) {
      size += pb::CodedOutputStream.ComputeInt64Size(5, TermId);
    }
    size += UnknownFields.SerializedSize;
    memoizedSerializedSize = size;
    return size;
  }
  public static CharacterDTOPB ParseFrom(pb::ByteString data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(byte[] data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(global::System.IO.Stream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  public static CharacterDTOPB ParseDelimitedFrom(global::System.IO.Stream input) {
    return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
  }
  public static CharacterDTOPB ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(pb::ICodedInputStream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static CharacterDTOPB ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  private CharacterDTOPB MakeReadOnly() {
    return this;
  }
  
  public static Builder CreateBuilder() { return new Builder(); }
  public override Builder ToBuilder() { return CreateBuilder(this); }
  public override Builder CreateBuilderForType() { return new Builder(); }
  public static Builder CreateBuilder(CharacterDTOPB prototype) {
    return new Builder(prototype);
  }
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  public sealed partial class Builder : pb::GeneratedBuilder<CharacterDTOPB, Builder> {
    protected override Builder ThisBuilder {
      get { return this; }
    }
    public Builder() {
      result = DefaultInstance;
      resultIsReadOnly = true;
    }
    internal Builder(CharacterDTOPB cloneFrom) {
      result = cloneFrom;
      resultIsReadOnly = true;
    }
    
    private bool resultIsReadOnly;
    private CharacterDTOPB result;
    
    private CharacterDTOPB PrepareBuilder() {
      if (resultIsReadOnly) {
        CharacterDTOPB original = result;
        result = new CharacterDTOPB();
        resultIsReadOnly = false;
        MergeFrom(original);
      }
      return result;
    }
    
    public override bool IsInitialized {
      get { return result.IsInitialized; }
    }
    
    protected override CharacterDTOPB MessageBeingBuilt {
      get { return PrepareBuilder(); }
    }
    
    public override Builder Clear() {
      result = DefaultInstance;
      resultIsReadOnly = true;
      return this;
    }
    
    public override Builder Clone() {
      if (resultIsReadOnly) {
        return new Builder(result);
      } else {
        return new Builder().MergeFrom(result);
      }
    }
    
    public override pbd::MessageDescriptor DescriptorForType {
      get { return global::CharacterDTOPB.Descriptor; }
    }
    
    public override CharacterDTOPB DefaultInstanceForType {
      get { return global::CharacterDTOPB.DefaultInstance; }
    }
    
    public override CharacterDTOPB BuildPartial() {
      if (resultIsReadOnly) {
        return result;
      }
      resultIsReadOnly = true;
      return result.MakeReadOnly();
    }
    
    public override Builder MergeFrom(pb::IMessage other) {
      if (other is CharacterDTOPB) {
        return MergeFrom((CharacterDTOPB) other);
      } else {
        base.MergeFrom(other);
        return this;
      }
    }
    
    public override Builder MergeFrom(CharacterDTOPB other) {
      if (other == global::CharacterDTOPB.DefaultInstance) return this;
      PrepareBuilder();
      if (other.HasAccountId) {
        AccountId = other.AccountId;
      }
      if (other.HasPlayerId) {
        PlayerId = other.PlayerId;
      }
      if (other.HasSresId) {
        SresId = other.SresId;
      }
      if (other.HasMapId) {
        MapId = other.MapId;
      }
      if (other.HasTermId) {
        TermId = other.TermId;
      }
      this.MergeUnknownFields(other.UnknownFields);
      return this;
    }
    
    public override Builder MergeFrom(pb::ICodedInputStream input) {
      return MergeFrom(input, pb::ExtensionRegistry.Empty);
    }
    
    public override Builder MergeFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
      PrepareBuilder();
      pb::UnknownFieldSet.Builder unknownFields = null;
      uint tag;
      string field_name;
      while (input.ReadTag(out tag, out field_name)) {
        if(tag == 0 && field_name != null) {
          int field_ordinal = global::System.Array.BinarySearch(_characterDTOPBFieldNames, field_name, global::System.StringComparer.Ordinal);
          if(field_ordinal >= 0)
            tag = _characterDTOPBFieldTags[field_ordinal];
          else {
            if (unknownFields == null) {
              unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
            }
            ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
            continue;
          }
        }
        switch (tag) {
          case 0: {
            throw pb::InvalidProtocolBufferException.InvalidTag();
          }
          default: {
            if (pb::WireFormat.IsEndGroupTag(tag)) {
              if (unknownFields != null) {
                this.UnknownFields = unknownFields.Build();
              }
              return this;
            }
            if (unknownFields == null) {
              unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
            }
            ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
            break;
          }
          case 8: {
            result.hasAccountId = input.ReadInt64(ref result.accountId_);
            break;
          }
          case 16: {
            result.hasPlayerId = input.ReadInt64(ref result.playerId_);
            break;
          }
          case 24: {
            result.hasSresId = input.ReadInt64(ref result.sresId_);
            break;
          }
          case 32: {
            result.hasMapId = input.ReadInt64(ref result.mapId_);
            break;
          }
          case 40: {
            result.hasTermId = input.ReadInt64(ref result.termId_);
            break;
          }
        }
      }
      
      if (unknownFields != null) {
        this.UnknownFields = unknownFields.Build();
      }
      return this;
    }
    
    
    public bool HasAccountId {
      get { return result.hasAccountId; }
    }
    public long AccountId {
      get { return result.AccountId; }
      set { SetAccountId(value); }
    }
    public Builder SetAccountId(long value) {
      PrepareBuilder();
      result.hasAccountId = true;
      result.accountId_ = value;
      return this;
    }
    public Builder ClearAccountId() {
      PrepareBuilder();
      result.hasAccountId = false;
      result.accountId_ = 0L;
      return this;
    }
    
    public bool HasPlayerId {
      get { return result.hasPlayerId; }
    }
    public long PlayerId {
      get { return result.PlayerId; }
      set { SetPlayerId(value); }
    }
    public Builder SetPlayerId(long value) {
      PrepareBuilder();
      result.hasPlayerId = true;
      result.playerId_ = value;
      return this;
    }
    public Builder ClearPlayerId() {
      PrepareBuilder();
      result.hasPlayerId = false;
      result.playerId_ = 0L;
      return this;
    }
    
    public bool HasSresId {
      get { return result.hasSresId; }
    }
    public long SresId {
      get { return result.SresId; }
      set { SetSresId(value); }
    }
    public Builder SetSresId(long value) {
      PrepareBuilder();
      result.hasSresId = true;
      result.sresId_ = value;
      return this;
    }
    public Builder ClearSresId() {
      PrepareBuilder();
      result.hasSresId = false;
      result.sresId_ = 0L;
      return this;
    }
    
    public bool HasMapId {
      get { return result.hasMapId; }
    }
    public long MapId {
      get { return result.MapId; }
      set { SetMapId(value); }
    }
    public Builder SetMapId(long value) {
      PrepareBuilder();
      result.hasMapId = true;
      result.mapId_ = value;
      return this;
    }
    public Builder ClearMapId() {
      PrepareBuilder();
      result.hasMapId = false;
      result.mapId_ = 0L;
      return this;
    }
    
    public bool HasTermId {
      get { return result.hasTermId; }
    }
    public long TermId {
      get { return result.TermId; }
      set { SetTermId(value); }
    }
    public Builder SetTermId(long value) {
      PrepareBuilder();
      result.hasTermId = true;
      result.termId_ = value;
      return this;
    }
    public Builder ClearTermId() {
      PrepareBuilder();
      result.hasTermId = false;
      result.termId_ = 0L;
      return this;
    }
  }
  static CharacterDTOPB() {
    object.ReferenceEquals(global::CharacterDTODef.Descriptor, null);
  }
}

#endregion


#endregion Designer generated code